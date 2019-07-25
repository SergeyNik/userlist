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
        dialog: false,
        headers: [
            {
                text: 'ID',
                align: 'left',
                sortable: false,
                value: 'id'
            },
            { text: 'Наименование', value: 'name' },
            { text: 'Кому', value: 'to' },
            { text: 'От кого', value: 'from' },
            { text: 'Статус', value: 'status' },
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

        responseClaim: {
            id: '',
            name: 0,
            claimFrom: {
                id: 0,
                name: ''
            },
            claimTo: {
                id: 0,
                name: ''
            },
            claimStatus: {
                id: 0,
                name: ''
            }
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
    },

    created () {
        this.initialize()
    },

    methods: {
        async downloadClaims() {
            try {
                let response = await axios.get('/claim');
                response.data.forEach(claim => this.claims.push(claim))
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async downloadClaimsTo() {
            try {
                let response = await axios.get('/list/to');
                response.data.forEach(to => this.listTo.push(to))
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async downloadClaimsFrom() {
            try {
                let response = await axios.get('/list/from');
                response.data.forEach(from => this.listFrom.push(from))
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async downloadClaimsStatus() {
            try {
                let response = await axios.get('/list/status');
                response.data.forEach(status => this.listStatus.push(status))
            } catch (error) {
                snack.errorMessage(error)
            }
        },

        async createClaim() {
            return await axios.post('/claim', this.editedItem)
        },

        async updateClaim(id) {
            return await axios.put(`/claim/${id}`, this.editedItem)
        },

        initialize () {
            this.downloadClaimsTo();
            this.downloadClaimsFrom();
            this.downloadClaimsStatus();
            this.downloadClaims();
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

        deleteItem (item) {
            const index = this.claims.indexOf(item)
            confirm('Are you sure you want to delete this item?') && this.claims.splice(index, 1)
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