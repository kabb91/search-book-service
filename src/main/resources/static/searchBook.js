
/**
 * 책 서비스 검색 화면
 */
var SearchBook = Vue.extend({
    template: '#search-book',
    beforeCreate() {
    	if(sessionStorage.getItem('userId')==null){
    		alert("잘못된 접근입니다. 로그인 화면으로 이동 합니다.");
    		this.$router.push({
                name: 'user-login-page'
            })
    	}
    },
    data() {
        return {
            search: '',
            select: 'total',
            pageArray: [],
            totalCount: 0,
            pagesize: 10,
            currentPage: 1,
            keywordList: []

        }
    },
    mounted() {
        this.getKeywordRank();
    },
    methods: {
    	

        searchBar: function () {
        	
            axios.get('/search/book?' + 'target=' + this.select + '&query=' + this.search + "&size=10" + "&page=" + this.currentPage)
                .then(response => {

                	this.searchHistroy();
                    this.pageArray = response.data.books;
                    this.totalCount = parseInt(response.data.meta.total_count / 10) + 1;
                    
                    this.getKeywordRank();
                   

                }).catch(error => console.log(error))
        },
        current_change: function (currentPage) {
            this.currentPage = currentPage;
            axios.get('/search/book?' + 'target=' + this.select + '&query=' + this.search + "&size=10" + "&page=" + this.currentPage)
                .then(response => {
                    this.pageArray = response.data.books;

                }).catch(error => console.log(error))
        },
        searchHistroy() {
            var data = { "query": this.search, "userId": sessionStorage.getItem('userId') }
            axios.post('/history/', data).then((response) => {


            }).catch(error => console.log(error))
        },
        getKeywordRank() {
            axios.get('/history/keyword/top10')
                .then(response => {
                    this.keywordList = response.data;

                }).catch(error => console.log(error))
        },
        goToHistoryPage() {
            this.$router.push({
                name: 'my-history-page'
            })
        },
        logOutPage() {
            sessionStorage.removeItem('userId')
            this.$router.push({
                name: 'user-login-page'
            })
        }

    }

});

/**
* 내 검색 히스토리
*/
var MySearchHistory = Vue.extend({
    template: '#my-history-page',
    beforeCreate() {
    	if(sessionStorage.getItem('userId')==null){
    		alert("잘못된 접근입니다. 로그인 화면으로 이동 합니다.");

    		this.$router.push({
                name: 'user-login-page'
            })
    	}
    },
    data() {
        return {
            history: [],
        }

    },
    mounted() {

        var userId = sessionStorage.getItem('userId');
        axios.get('/history/' + userId)
            .then(response => {
                this.history = response.data;

            }).catch(error => console.log(error))
    },
    methods: {
        goToSearchPage() {
            this.$router.push({
                name: 'search-book'
            })
        },
        logOutPage() {
            sessionStorage.removeItem('userId')
            this.$router.push({
                name: 'user-login-page'
            })
        }
    }


});


/**
* 회원가입 화면
*/
var UserRegister = Vue.extend({
    template: '#user-register-page',

    data() {
        return {
            ruleForm: {
                id: '',
                password: '',
                name: ''

            },
            rules: {
                id: [
                    { required: true, message: '아이디를 입력해 주세요', trigger: 'blur' },
                    { min: 1, max: 15, message: '아이디는 1~15자리 자리 까지 만들 수 있습니다.', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '비밀번호를 입력해 주세요.', trigger: 'blur' },
                    { min: 1, max: 50, message: '비밀번호는 1자리이상 부터 입력가능합니.', trigger: 'blur' }
                ],
                name: [
                    { required: true, message: '이름을 입력해 주세요.', trigger: 'blur' },
                    { min: 2, max: 50, message: '이름은 두 글자 이상은 써주셔야합니다.', trigger: 'blur' }
                ],
            }
        }
    },
    methods: {

        onSubmit: function (ruleForm) {

            if (this.ruleForm.id == '') {
                alert('아이디를 입력해 주세요.');
            } else if (this.ruleForm.password == '') {
                alert('비밀번호를 입력해 주세요.');
            } else if (this.ruleForm.name == '') {
                alert('이름을 입력해 주세요.');
            } else {
                axios.get('/user/' + this.ruleForm.id + '/check')
                    .then(response => {
                        if (response.data == false) {

                            var data = { "userId": this.ruleForm.id, "userPassword": this.ruleForm.password, "userName": this.ruleForm.name };

                            axios.post('/user/', data).then((response) => {
                                alert('회원가입이 완료 되었습니다.');
                                this.goToPages();
                            }).catch(error => console.log(error))
                        } else {
                            alert('사용중인 아이디 입니다.');
                        }

                    }).catch(error => console.log(error))
            }

        },
        goToPages() {
            this.$router.push({
                name: 'user-login-page'
            })
        }

    }
});

/**
* 로그인 화면
*/
var UserLogin = Vue.extend({
    template: '#user-login-page',

    data() {
        return {
            ruleForm: {
                id: '',
                password: '',
                name: ''
            },
            rules: {
                id: [
                    { required: true, message: '아이디를 입력해 주세요', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '비밀번호를 입력해 주세요.', trigger: 'blur' },
                ]
            }
        }
    },
    methods: {

        onSubmit: function (ruleForm) {

            if (this.ruleForm.id == '') {
                alert('아이디를 입력해 주세요.');
            } else if (this.ruleForm.password == '') {
                alert('비밀번호를 입력해 주세요.');
            } else {
                axios.get('/user/' + this.ruleForm.id + '/' + this.ruleForm.id + '/check')
                    .then(response => {
                        if (response.data == true) {
                            alert("로그인에 성공");
                            sessionStorage.setItem('userId', this.ruleForm.id)
                            this.goToSearchBook();
                        } else {
                            alert("로그인에 실패하였습니다.");
                        }

                    }).catch(error => console.log(error))
            }


        },
        goToSearchBook() {
            this.$router.push({
                name: 'search-book'
            })
        },
        goToPages: function () {
            this.$router.push({
                name: 'user-register-page'
            })
        }

    }
});

/**
* Vue Router 부분
*/
var router = new VueRouter({
    routes: [
        { path: '/', component: UserLogin, name: 'user-login-page' },
        { path: '/login', component: UserLogin, name: 'user-login-page' },
        { path: '/register', component: UserRegister, name: 'user-register-page' },
        { path: '/history', component: MySearchHistory, name: 'my-history-page' },
        { path: '/search', component: SearchBook, name: 'search-book' }

    ]
});

new Vue({
    router
}).$mount('#app')