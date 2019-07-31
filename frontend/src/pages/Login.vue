<template>
  <v-app id="inspire">
    <v-content>
      <v-container
        fluid
        fill-height
      >
        <v-layout
          align-center
          justify-center
        >
          <v-flex
            xs12
            sm8
            md4
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>Login form</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model="userName"
                    label="Login"
                    name="login"
                    prepend-icon="person"
                    type="text"
                  ></v-text-field>

                  <v-text-field
                    v-model="password"
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="lock"
                    type="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="setup">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>
<script>
  export default {
    data() {
      return {
        logout: false,
        error: false,
        loading: false,
        userName: '',
        password: '',
        drawer: null
      }
    },
    mounted() {
      // console.log('mounted')
    },
    methods: {
      async setup() {
        let body = JSON.stringify({'username': this.userName, 'password': this.password})


        await this.$store.dispatch('login', {'username': this.userName, 'password': this.password})
        this.$router.push({name: 'home'});
        console.log('route home')
      },
      // submit () {
      //   if (!/^\s*%/.test(this.userName) && !/^\s*%/.test(this.password)) {
      //     let body = JSON.stringify({'username': this.userName, 'password': this.password})
      //     this.loading = true
      //     fetch('http://127.0.0.1:9001/', {
      //       method: 'POST',
      //       mode: 'cors',
      //       body: body
      //     }).then((res) => {
      //       // console.log(res)
      //       if (res.status === 200) {
      //         res.json().then((data) => {
      //           // console.log(data.token, this.userName)
      //           sessionStorage.setItem('token', data.token)
      //           // sessionStorage.setItem('user_name', this.userName)
      //           // console.log(this.$router)
      //           this.$router.replace('/')
      //         })
      //       } else {
      //         this.error = true
      //       }
      //       this.loading = false
      //       // this.$emit('success')
      //     }).catch((e) => {
      //       this.error = true
      //       this.loading = false
      //     })
      //   }
      // }
    }
  }
</script>
<style>
</style>
