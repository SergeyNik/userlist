const snack = new Vue({
    el: '#status',
    data () {
        return {
            snackbar: false,
            color: 'success',
            mode: '',
            timeout: 6000,
            text: ''
        }
    },
    methods: {
        successMessage: function(text) {
            snack.text = text;
            snack.color = 'success';
            snack.snackbar = true;
        },
        errorMessage: function(text, msg) {
            snack.color = "error";
            snack.text = text + msg;
            snack.snackbar = true;
        }
    }
});

const app = new Vue({
    el: '#app',
    data: () => ({
        totalItems: 0,
        dialog: false,
        pagination: {},
        rowsPerPageItems: [5, 10, 25],
        headers: [
            {
                text: 'ID',
                align: 'left',
                sortable: false,
                value: 'id'
            },
            { text: 'Наименование', value: 'name', sortable: false },
            { text: 'Кому', value: 'to', sortable: false },
            { text: 'От кого', value: 'from', sortable: false },
            { text: 'Статус', value: 'status', sortable: false },
            { text: 'Actions', value: 'name', sortable: false }
        ],

        comboSelect: {
            to: '',
            from: '',
            status: ''
        },
        select:'',
        claims: [],
        listTo: [],
        listFrom: [],
        listStatus: [],
        editedIndex: -1,
        editedItem: {
            id: '',
            name: '',
            claimTo: {
                id: '',
                name: ''
            },
            claimFrom: {
                id: '',
                name: ''
            },
            claimStatus: {
                id: '',
                name: ''
            },
        },

        defaultItem: {
            id: '',
            name: '',
            claimTo: {
                id: '',
                name: ''
            },
            claimFrom: {
                id: '',
                name: ''
            },
            claimStatus: {
                id: '',
                name: ''
            },
        },
    }),

    computed: {
        formTitle () {
            return this.editedIndex === -1 ? 'Новая заявка' : 'Редактировать'
        },
    },

    watch: {
        dialog (val) {
            val || this.close()
        },
        pagination: {
            handler () {
                this.downloadClaims(this.pagination.page - 1, this.pagination.rowsPerPage);
            },
            deep: true
        },
    },

    created () {
        this.initialize()
    },

    methods: {
        async downloadClaims(page, size) {
            try {
                let response = await axios.get('/claim',  { params: { page: page , size: size} });
                this.claims = response.data
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async downloadClaimsTo() {
            try {
                let response = await axios.get('/list/to');
                this.listTo = response.data
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async downloadClaimsFrom() {
            try {
                let response = await axios.get('/list/from');
                this.listFrom = response.data
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async downloadClaimsStatus() {
            try {
                let response = await axios.get('/list/status');
                this.listStatus = response.data
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async countAllClaims() {
            try {
                let response = await axios.get('/list/count');
                this.totalItems = response.data
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async deleteClaim(id) {
            return await axios.delete(`/claim/${id}`);
        },

        async createClaim() {
            return await axios.post('/claim', this.editedItem)
        },

        async updateClaim(id) {
            return await axios.put(`/claim/${id}`, this.editedItem)
        },

        initialize () {
            this.countAllClaims();
            this.downloadClaimsTo();
            this.downloadClaimsFrom();
            this.downloadClaimsStatus();
        },

        editItem (itemId) {
            this.editedIndex = this.claims.findIndex(function (val) {
                return val.id === itemId;
            });

            this.editedItem = this.claims.find(function (val) {
                return val.id === itemId;
            });

            this.dialog = true
        },

        deleteItem (id) {
            console.log(id);
            if (confirm('Удалить заявку?')) {
                this.deleteClaim(id)
                    .then((response) => {
                        const index = this.claims.indexOf(id);
                        this.claims.splice(index, 1)
                    })
                    .catch(function (error) {
                        snack.errorMessage(error);
                    });
            }
        },

        close () {
            this.dialog = false
            setTimeout(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
            }, 300)
        },

        save () {
            if (this.editedIndex > -1) {
                this.updateClaim(this.editedItem.id)
                    .then(function (response) {
                        Object.assign(this.claims[this.editedIndex], this.editedItem)
                    })
                    .catch(function (error) {
                        snack.errorMessage(error);
                    });
            } else {
                this.createClaim()
                    .then((response) => {
                        this.claims.push(response.data)
                    })
                    .catch(function (error) {
                        snack.errorMessage(error);
                    });
            }
            console.log(this.claims);
            this.close()
        }
    }
});