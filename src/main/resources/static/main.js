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

        claims: [],
        editedIndex: -1,
        editedItem: {
            id: '',
            name: '',
            to: '',
            from: '',
            status: ''
        },
        defaultItem: {
            id: '',
            name: '',
            to: '',
            from: '',
            status: ''
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
        }
    },

    watch: {
        dialog (val) {
            val || this.close()
        }
    },

    created () {
        // this.initialize()
    },

    methods: {
        async downloadClaims() {
            try {
                let response = await axios.get('/claim');
                // console.log(response.data);
                response.data.forEach(claim => this.claims.push(
                    {
                        id: claim.id,
                        name: claim.name,
                        to: claim.claimTo.name,
                        from: claim.claimFrom.name,
                        status: claim.claimStatus.name,
                    })
                )
            } catch (error) {
                // snack.errorMessage(error)
            }
        },

        initialize () {
            this.downloadClaims();
        },

        editItem (item) {
            this.editedIndex = this.claims.indexOf(item)
            this.editedItem = Object.assign({}, item)
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
                Object.assign(this.claims[this.editedIndex], this.editedItem)
            } else {
                this.claims.push(this.editedItem)
            }
            this.close()
        }
    }
});

const snack = new Vue({
    el: '#status',
    data () {
        return {
            snackbar: false,
            color: 'success', // or error
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