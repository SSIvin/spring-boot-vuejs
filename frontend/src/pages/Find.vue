<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <v-alert
                type="success"
                :value="alert"
                outline

        >
            Loading...
        </v-alert>
        <!--<v-container fluid>-->
        <!--<v-layout row>-->
        <!--<v-flex xs6 order-lg2>-->
        <!--<v-card dark tile flat color="error">-->
        <!--<v-card-text>#1</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--<v-flex xs6>-->
        <!--<v-card dark tile flat color="red darken-4">-->
        <!--<v-card-text>#2</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--</v-layout>-->
        <!--<v-layout row>-->
        <!--<v-flex xs4 order-md2 order-xs1>-->
        <!--<v-card dark tile flat color="red lighten-1">-->
        <!--<v-card-text>#1</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--<v-flex xs4 order-md3 order-xs2>-->
        <!--<v-card dark tile flat color="red lighten-2">-->
        <!--<v-card-text>#2</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--<v-flex xs4 order-md1 order-xs3>-->
        <!--<v-card dark tile flat color="red darken-1">-->
        <!--<v-card-text>#3</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--</v-layout>-->
        <!--<v-layout row wrap>-->
        <!--<v-flex xs12 sm6 md3 order-md4 order-sm2>-->
        <!--<v-card dark tile flat color="red darken-2">-->
        <!--<v-card-text>#1</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--<v-flex xs12 sm6 md3 order-md3 order-sm1>-->
        <!--<v-card dark tile flat color="deep-orange lighten-1">-->
        <!--<v-card-text>#2</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--<v-flex xs12 sm6 md3 order-md2 order-sm4>-->
        <!--<v-card dark tile flat color="deep-orange darken-3">-->
        <!--<v-card-text>#3</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--<v-flex xs12 sm6 md3 order-md1 order-sm3>-->
        <!--<v-card dark tile flat color="deep-orange">-->
        <!--<v-card-text>#4</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--</v-layout>-->
        <!--</v-container>-->

        <!--<v-container grid-list-md>-->
        <!--<v-layout row>-->
        <!--<v-flex v-for="i in 2" :key="`6${i}`" xs6 pa-5>-->
        <!--<v-card dark color="secondary">-->
        <!--<v-card-text class="px-0">6</v-card-text>-->
        <!--</v-card>-->
        <!--</v-flex>-->
        <!--</v-layout>-->
        <!--</v-container>-->

        <v-container>
            <v-form

                    ref="form"
                    v-model="valid"
                    lazy-validation
                    v-on:submit="findPerson"
                    method="post"
                    action="#"
            >
                <v-layout row>
                    <v-flex xs-6 pr-5>
                        <v-text-field
                                clearable
                                v-model="f"
                                :counter="30"
                                label="Фамилия"
                                :rules="nameRules"
                                required
                        ></v-text-field>
                    </v-flex>
                    <v-flex xs-6 pr-5>
                        <v-menu
                                v-model="menu2"
                                :close-on-content-click="false"
                                :nudge-right="40"
                                lazy
                                hint="DD/MM/YYYY format"
                                transition="scale-transition"
                                offset-y
                                full-width
                                max-width="290px"
                                min-width="290px"
                        >
                            <template v-slot:activator="{ on }">
                                <v-text-field
                                        v-model="computedDateFormatted"
                                        clearable
                                        label="Дата рождения"
                                        prepend-icon="event"
                                        readonly
                                        v-on="on"
                                ></v-text-field>
                            </template>
                            <v-date-picker
                                    v-model="date"
                                    no-title @input="menu2 = false"
                            >
                            </v-date-picker>
                        </v-menu>
                    </v-flex>
                </v-layout>
                <v-layout row wrap>
                    <v-flex xs6 pr-5>
                        <v-text-field

                                v-model="i"
                                :counter="30"
                                label="Имя"
                                :rules="nameRules"
                                required
                        ></v-text-field>
                    </v-flex>
                    <v-flex xs6 pr-5>
                        <v-text-field
                                v-model="o"
                                :counter="30"
                                label="Отчество"
                                required
                        ></v-text-field>
                    </v-flex>
                </v-layout>
            </v-form>
            <v-layout>
                <v-btn
                        :disabled="!valid"
                        color="orange"
                        @click="findPerson"
                >
                    Find
                </v-btn>
                <v-btn
                        :to="{
                                name: 'addPerson'
                             }"
                        color="green"
                >
                    Add
                </v-btn>
                <v-btn
                        @click="OnAlert"
                ></v-btn>
            </v-layout>

            <span></span>

            <v-flex xs12 class="text-xs-center">
                <v-progress-circular
                        indeterminate
                        class="primary--text"
                        :width="4"
                        :size="50"
                        v-if="loading"
                ></v-progress-circular>
            </v-flex>

            <v-container>
                <app-person v-if="finishLoad"></app-person>
            </v-container>

            <!--получение справочников-->

            <!--при загрузке странице-->
            <!--@focus.native="selectAll"-->
            <v-select
                    browserAutocomplete="off"
                    item-text="name"
                    item-value="id"
                    v-bind:items="getplace_job"
                    v-model="selectedYear">
            </v-select>
            <p>{{selectedPlace}}</p>
            <!--По кнопке-->
            <v-btn
                    @click="GET_PLACE_JOB"
            >get</v-btn>

            <!--Изменение State-->
            <v-btn
                    @click="changeState"
            >changeState</v-btn>

            <!--<p>{{place_job}}</p>-->
            <li v-for="item in getplace_job">
                {{ item.name }}
            </li>

            <!----------------------------------------->

        </v-container>

    </div>
</template>

<script>
import Persons from './Persons.vue'
import { mapActions, mapGetters, mapMutations } from 'vuex'

export default {
  data () {
    return {
      valid: true,
      selectedYear: null,
      id: 0,
      f: '',
      i: '',
      o: '',
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      alert: false,
      nameRules: [
        v => !!v || 'Поле обязательное'
      ],
      finishLoad: false,
      items: [],
      person: {
        id: 1,
        name: 'Иванов',
        placeJob: 2
      }

    }
  },
  computed: {
    computedDateFormatted () {
      return this.formatDate(this.date)
    },
    loading () {
      return this.$store.getters.getProcessing
    },

    ...mapGetters(['getplace_job']),
    storeItems () {
      return [
        {
          id: 1,
          name: 'Foo1' },
        {
          id: 2,
          name: 'Bar' }
      ]
    },
    selectedPlace () {
      return this.selectedYear
    }
  },
  methods: {
    ...mapMutations(['changeState']),
    ...mapActions(['GET_PLACE_JOB']),
    // find() {
    //     if (this.$refs.form.validate()) {
    //         this.snackbar = true
    //     }
    // },
    // getPlaceJob() {
    //     this.items = this.storeItems
    // },
    formatDate (date) {
      if (!date) return null

      const [year, month, day] = date.split('-')
      return `${day}/${month}/${year}`
    },
    // changeState() {
    //     this.$store.commit('changeState')
    // },
    // getPlaceJob() {
    //     this.$store.dispatch('GET_PLACE_JOB');
    //     // console.log(this.place_job);
    //
    // },
    OnAlert () {
      // this.$store.commit('SET_PROCESSING',true)
      // this.alert = !this.alert;
      // alert('Please add a valid file!')
    },
    findPerson () {
      // this.$store.dispatch('persons', '')
      if ((this.$refs.form.validate())) {
        const data = {
          id: this.id,
          f: this.f,
          i: this.i,
          o: this.o
        }

        // this.alert = true;
        this.$store.dispatch('GET_personAction', data)
        // this.alert = false
        this.finishLoad = true
      }
    }
  },
  mounted () {
    this.$store.dispatch('GET_PLACE_JOB')
  },
  created () {
    this.selectedYear = this.person.placeJob
  },
  components: {
    appPerson: Persons
  }

}
</script>

<style scoped>

</style>
