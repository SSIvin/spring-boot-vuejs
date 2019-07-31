<template>
    <div>
        <v-container fluid grid-list-md>
            <v-label>Дело №:{{person.id}}</v-label>
            <form v-on:submit="updateItem">
                <v-layout wrap>
                    <v-flex d-flex xs12 sm6 md6>
                        <v-layout wrap>
                            <v-flex xs12 sm6>
                                <v-text-field
                                        v-model="person.f"
                                        label="Фамилия"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6>
                                <v-text-field
                                        v-model="person.i"
                                        label="Имя"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6>
                                <v-text-field
                                        v-model="person.o"
                                        label="Отчество"
                                ></v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex d-flex xs12 sm6 md4>
                        <v-layout wrap>
                            <v-flex d-flex>
                                <v-layout wrap>
                                    <v-select
                                            label="Место работы"
                                            browserAutocomplete="off"
                                            item-text="name"
                                            item-value="id"
                                            :items="getplace_job"
                                            v-model="person.placeJob">
                                    </v-select>
                                    <v-menu ref="BirthDateMenuRef"
                                            v-model="BirthDateMenuVisibility" :close-on-content-click="false"
                                            :nudge-right="40" lazy transition="scale-transition" offset-y full-width
                                            min-width="290px">
                                        <v-text-field slot="activator" v-model="BirthDateLocal" label="Дата рождения"
                                                      prepend-icon="event" readonly></v-text-field>
                                        <v-date-picker v-model="BirthDateLocal"
                                                       no-title @input="$refs.BirthDateMenuRef.save(BirthDateLocal);"
                                        >

                                        </v-date-picker>
                                    </v-menu>
                                </v-layout>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
                <v-btn
                        @click.prevent="updateItem"
                        class="btn btn-primary"
                        flat=""
                > {{buttonTitle}}
                </v-btn>
                <v-btn
                        :to="{name:'find'}"
                        type="button"
                        class="btn btn-primary"
                        flat=""
                >Close
                </v-btn>
                <v-btn
                        @click=""
                        class="btn btn-primary"
                        flat=""
                > get
                </v-btn>

                <v-btn
                        @click="modalWindow('dialogSignIn',true)"
                        class="btn btn-primary"
                        flat=""
                > dialogSignIn
                </v-btn>
            </form>
        </v-container>

        <!--<v-label>{{lableTitle}}</v-label>-->
        <!--<form v-on:submit="updateItem">-->
        <!--<div>Дело:{{person.id}}</div>-->
        <!--<div class="col-md-12 mt-3">-->
        <!--&lt;!&ndash;Дело №: {{find.id}}&ndash;&gt;-->
        <!--<h4></h4>-->
        <!--<v-layout col color="purple">-->
        <!--<v-flex color="purple" xs3>-->
        <!--<div-->
        <!--class="form-group"-->
        <!--&gt;-->
        <!--<label>Фамилия</label>-->
        <!--<input type="text" class="form-control" placeholder="Фамилия" v-model="person.f">-->
        <!--</div>-->
        <!--<div class="form-group">-->
        <!--<label>Имя</label>-->
        <!--<input type="text" class="form-control" placeholder="Имя" v-model="person.i">-->
        <!--</div>-->
        <!--<div class="form-group">-->
        <!--<label>Отчество</label>-->
        <!--<input type="text" class="form-control" placeholder="Отчество" v-model="person.o">-->
        <!--</div>-->
        <!--</v-flex>-->
        <!--</v-layout>-->
        <!--<v-layout row>-->
        <!--<v-flex xs3>-->

        <!--<v-menu ref="BirthDateMenuRef"-->
        <!--v-model="BirthDateMenuVisibility" :close-on-content-click="false"-->
        <!--:nudge-right="40" lazy transition="scale-transition" offset-y full-width-->
        <!--min-width="290px">-->
        <!--<v-text-field slot="activator" v-model="BirthDateLocal" label="Дата рождения"-->
        <!--prepend-icon="event" readonly></v-text-field>-->
        <!--<v-date-picker v-model="BirthDateLocal"-->
        <!--no-title @input="$refs.BirthDateMenuRef.save(BirthDateLocal);"-->
        <!--&gt;-->

        <!--</v-date-picker>-->
        <!--</v-menu>-->
        <!--</v-flex>-->
        <!--</v-layout>-->
        <!--<v-flex xs3>-->
        <!--<v-select-->
        <!--label="Место работы"-->
        <!--browserAutocomplete="off"-->
        <!--item-text="name"-->
        <!--item-value="id"-->
        <!--:items="getplace_job"-->
        <!--v-model="person.placeJob">-->
        <!--</v-select>-->
        <!--</v-flex>-->
        <!--</div>-->
        <!--<v-btn-->
        <!--@click.prevent="updateItem"-->
        <!--class="btn btn-primary"-->
        <!--flat=""-->
        <!--&gt; {{buttonTitle}}-->
        <!--</v-btn>-->
        <!--<v-btn-->
        <!--:to="{name:'find'}"-->
        <!--type="button"-->
        <!--class="btn btn-primary"-->
        <!--flat=""-->
        <!--&gt;Close-->
        <!--</v-btn>-->
        <!--<v-btn-->
        <!--@click=""-->
        <!--class="btn btn-primary"-->
        <!--flat=""-->
        <!--&gt; get-->
        <!--</v-btn>-->

        <!--<v-btn-->
        <!--@click="modalWindow('dialogSignIn',true)"-->
        <!--class="btn btn-primary"-->
        <!--flat=""-->
        <!--&gt; dialogSignIn-->
        <!--</v-btn>-->

        <!--</form>-->

        <!--Simple Dialog-->
        <!--применил общую функцию для показа окон modalWindow('имя окна','показать/скрыть')-->
        <v-dialog v-model="modals.dialogSignIn" max-width="500px">
            <v-card>
                <v-card-title>
                    <span class="headline"></span>
                </v-card-title>
                <v-card-text>
                    <v-container grid-list-md>
                        <v-layout wrap>
                            <v-flex xs12 sm6 md4>
                                <v-text-field v-model="person.f" label="Фамилия"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                                <v-text-field v-model="person.i" label="Имя"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                                <v-text-field v-model="person.o" label="Отчество"></v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <div class="modal-footer">
                        <!--<v-btn type="button" class="btn btn-white" @click="$emit('close')">Close</v-btn>-->
                        <v-btn type="button" class="btn btn-white" @click="modalWindow('dialogSignIn',false)">Close
                        </v-btn>
                        <!--<button type="button" class="btn btn-primary" data-dismiss="modal" :click="saveEdit">Save changes</button>-->
                    </div>
                </v-card-actions>
            </v-card>
        </v-dialog>
        <!---------------------------------------------------->


    </div>
</template>

<script>
    import {mapState, mapGetters} from 'vuex';

    export default {
        props: ['id'],
        data() {
            return {
                BirthDateMenuVisibility: false,
                modal: false,
                lorem: 'Lorem ipsum dolor sit amet, mel at clita quando. Te sit oratio vituperatoribus, nam ad ipsum posidonium mediocritatem, explicari dissentiunt cu mea. Repudiare disputationi vim in, mollis iriure nec cu, alienum argumentum ius ad. Pri eu justo aeque torquatos.'
            }
        },
        methods: {
            updateItem() {
                if (this.person.id != 0) {
                    this.$store.dispatch('updateMessageAction', this.person);
                    console.log('save');
                } else {
                    const newperson = {
                        f: this.person.f,
                        i: this.person.i,
                        o: this.person.o,
                    }
                    this.$store.dispatch('CreateMessageAction', newperson);
                    console.log('add person');
                }

                this.$router.push({name: 'find'});
            },
            settest() {

                console.log(this.listPersonOne.f)
            },
            modalWindow(name, show) {
                this.$store.commit('dialogShow', {name, show});
            },
        },
        destroyed() {
            this.$store.commit('CLEAR_PERSON');
        },

        mounted() {

            if (this.$route.params.id) {
                this.$store.dispatch('GET_PLACE_JOB');
                this.$store.dispatch('GET_personOneAction', this.$route.params.id);


            }
        },
        computed: {
            ...mapState({
                person: state => state.person.listPersonOne,
                modals: state => state.person.modals

            }),
            ...mapGetters(['getplace_job']),
            // place() {
            //     return this.person.placeJob;
            // },
            // personOsn() {
            //     return this.$store.getters.getPersonOne
            // },


            // modals() {
            //     return this.$store.state.person.modals;
            // },

            BirthDateLocal: {
                get() {
                    return this.person.id ? this.person.birthday : "";
                },
                set(newValue) {
                    console.log('BirthDateLocal Setter: ' + newValue);
                    this.person.birthday = newValue
                    //this.$store.dispatch('update_currentEntity_byEntityPropertyContainer', {collectionId:this.componentCollectionId,propertiesObject:{BirthDate: newValue}});
                },
            },
            lableTitle() {

                return this.person.id ? 'Редактирование' : 'Новое дело'
            },
            buttonTitle() {
                return this.person.id ? 'Save' : 'Create'
            }
        }
    }
</script>

<style scoped>
    /*body {*/
        /*padding: 20px;*/
        /*font: 1em Helvetica Neue, Helvetica, Arial, sans-serif;*/
    /*}*/

    /** {box-sizing: border-box;}*/

    /*p {*/
        /*margin: 0 0 1em 0;*/
    /*}*/

    /*.container {*/
       /**/
        /*border: 1px solid rgb(111,41,97);*/
        /*border-radius: .5em;*/
        /*padding: 10px;*/
        /*display: grid;*/
        /*grid-template-columns: 1fr 1fr 1fr;*/
        /*grid-gap: 20px;*/
    /*}*/

    /*.container > div {*/
        /*padding: 1px;*/
        /*background-color: rgba(111,41,97,.3);*/
        /*border: 2px solid rgba(111,41,97,.5);*/
    /*}*/

    /*.container > div:nth-child(3n+1) {*/
        /*grid-row-end: span 2;*/
        /*background-color: rgba(193,225,237,.3);*/
        /*border: 2px solid rgba(193,225,237,.5);*/
    /*}*/



    .pink-box {
        border: 2px red solid;
        /* rest height ??? */
        /* height: 100%; */
    }

    .green-box {
        border: 2px green solid;
        /* rest height ??? */
        /* height: 100%; */
    }

    .red-box {
        border: 2px red solid;
        /* rest height ??? */
        /* height: 100%; */
    }
</style>

