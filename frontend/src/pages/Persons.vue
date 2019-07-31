<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container>
        <!--<h1>{{profile.name}}</h1>-->
        <h1>{{listPersonsCount}}</h1>
        <div>
            <v-data-table
                    :headers="headers"
                    :items="listPersons"
                    class="elevation-1"
                    hide-actions

            >
                <template slot="items" slot-scope="props">
                    <td>{{ props.item.id }}</td>
                    <td>{{ props.item.f }}</td>
                    <td>{{ props.item.i }}</td>
                    <td>{{ props.item.o }}</td>

                    <!--через новую страницу-->
                    <td>
                        <router-link
                                :to="{
                                    params: {id:props.item.id},
                                    name: 'OsnPerson'
                                    }"
                                :key="props.item.id"
                        >
                            <v-icon fab dark small
                                    color="primary">edit
                            </v-icon>
                        </router-link>

                        <!--Delete-->
                        <v-btn icon
                               href="#"
                               @click="deletePerson(props.item, props.login)"
                        >
                            <v-icon>delete</v-icon>
                        </v-btn>

                        <!--через модальное окно-->
                        <!--<edit-dialog :itemEdit="props.item"></edit-dialog>-->

                        <!---->
                        <v-btn fab dark small
                               color="primary"
                               @click="editPerson(props.item.id)"
                        >
                            <!--<v-progress-circular-->
                            <!--indeterminate-->
                            <!--color="primary"-->
                            <!--v-if="loading"-->
                            <!--&gt;</v-progress-circular>-->
                            <v-icon>edit</v-icon>
                        </v-btn>
                    </td>
                </template>
            </v-data-table>
        </div>

        <!--Simple Dialog-->
        <v-dialog v-model="dialog" max-width="500px">
            <v-card>
                <v-card-title>
                    <span class="headline">{{ formTitle }}</span>
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
                        <v-btn type="button" class="btn btn-white" @click="dialog=false">Close</v-btn>
                        <!--<button type="button" class="btn btn-primary" data-dismiss="modal" :click="saveEdit">Save changes</button>-->
                    </div>
                </v-card-actions>
            </v-card>
        </v-dialog>
        <!---------------------------------------------------->

        <!--FullScreen-->
        <template>
            <v-layout row justify-center>
                <v-dialog v-model="dialogFullScreen" fullscreen hide-overlay transition="dialog-bottom-transition">
                    <!--<template v-slot:activator="{ on }">-->
                    <!--<v-btn color="primary" dark v-on="on">Open Dialog</v-btn>-->
                    <!--</template>-->
                    <v-card>
                        <v-toolbar dark color="primary">
                            <v-btn icon dark @click="dialogFullScreen = false">
                                <v-icon>close</v-icon>
                            </v-btn>
                            <v-toolbar-title>Дело № {{this.person.id}}</v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-toolbar-items>
                                <v-btn dark flat @click="save">Save</v-btn>
                            </v-toolbar-items>
                        </v-toolbar>
                        <v-card-text>
                            <v-container grid-list-md>
                                <v-layout align-start column>
                                    <v-flex>
                                        <v-text-field v-model="person.f" label="Фамилия"></v-text-field>
                                    </v-flex>
                                    <v-flex>
                                        <v-text-field v-model="person.i" label="Имя"></v-text-field>
                                    </v-flex>
                                    <v-flex>
                                        <v-text-field v-model="person.o" label="Отчество"></v-text-field>
                                    </v-flex>
                                    <v-flex xs-6 pr-5>

                                        <v-menu ref="BirthDateMenuRef"
                                                v-model="BirthDateMenuVisibility" :close-on-content-click="false"
                                                :nudge-right="40" lazy transition="scale-transition" offset-y full-width
                                                min-width="290px">
                                            <v-text-field slot="activator" v-model="BirthDateLocal"
                                                          label="Дата рождения"
                                                          prepend-icon="event" readonly></v-text-field>
                                            <v-date-picker v-model="BirthDateLocal"
                                                           no-title
                                                           @input="$refs.BirthDateMenuRef.save(BirthDateLocal);"
                                            >

                                            </v-date-picker>
                                        </v-menu>

                                        <!--<v-menu-->
                                        <!--v-model="menu2"-->
                                        <!--:close-on-content-click="false"-->
                                        <!--:nudge-right="40"-->
                                        <!--lazy-->
                                        <!--hint="DD/MM/YYYY format"-->
                                        <!--transition="scale-transition"-->
                                        <!--offset-y-->
                                        <!--full-width-->
                                        <!--max-width="290px"-->
                                        <!--min-width="290px"-->
                                        <!--&gt;-->
                                        <!--<template v-slot:activator="{ on }">-->
                                        <!--<v-text-field-->
                                        <!--v-model="computedDateFormatted"-->
                                        <!--clearable-->
                                        <!--label="Дата рождения"-->
                                        <!--prepend-icon="event"-->
                                        <!--v-on="on"-->
                                        <!--&gt;</v-text-field>-->
                                        <!--</template>-->
                                        <!--<v-date-picker-->
                                        <!--v-model="myDate"-->
                                        <!--no-title @input="menu2 = false"-->
                                        <!--&gt;-->
                                        <!--</v-date-picker>-->
                                        <!--</v-menu>-->
                                    </v-flex>
                                </v-layout>

                                <v-container grid-list-md>
                                    <v-layout row wrap>
                                        <v-flex xs12 sm4>
                                            <v-select
                                                    :items="dropdown_font"
                                                    label="Overflow Btn"
                                                    target="#dropdown-example"
                                            ></v-select>
                                        </v-flex>
                                    </v-layout>
                                </v-container>
                            </v-container>
                        </v-card-text>
                        <v-list three-line subheader>
                            <v-subheader>User Controls</v-subheader>
                            <v-list-tile avatar>
                                <v-list-tile-content>
                                    <v-list-tile-title>Content filtering</v-list-tile-title>
                                    <v-list-tile-sub-title>Set the content filtering level to restrict apps that can be
                                        downloaded
                                    </v-list-tile-sub-title>
                                </v-list-tile-content>
                            </v-list-tile>
                            <v-list-tile avatar>
                                <v-list-tile-content>
                                    <v-list-tile-title>Password</v-list-tile-title>
                                    <v-list-tile-sub-title>Require password for purchase or use password to restrict
                                        purchase
                                    </v-list-tile-sub-title>
                                </v-list-tile-content>
                            </v-list-tile>
                        </v-list>
                        <v-divider></v-divider>
                        <v-list three-line subheader>
                            <v-subheader>General</v-subheader>
                            <v-list-tile avatar>
                                <v-list-tile-action>
                                    <v-checkbox v-model="notifications"></v-checkbox>
                                </v-list-tile-action>
                                <v-list-tile-content>
                                    <v-list-tile-title>Notifications</v-list-tile-title>
                                    <v-list-tile-sub-title>Notify me about updates to apps or games that I downloaded
                                    </v-list-tile-sub-title>
                                </v-list-tile-content>
                            </v-list-tile>
                            <v-list-tile avatar>
                                <v-list-tile-action>
                                    <v-checkbox v-model="sound"></v-checkbox>
                                </v-list-tile-action>
                                <v-list-tile-content>
                                    <v-list-tile-title>Sound</v-list-tile-title>
                                    <v-list-tile-sub-title>Auto-update apps at any time. Data charges may apply
                                    </v-list-tile-sub-title>
                                </v-list-tile-content>
                            </v-list-tile>
                            <v-list-tile avatar>
                                <v-list-tile-action>
                                    <v-checkbox v-model="widgets"></v-checkbox>
                                </v-list-tile-action>
                                <v-list-tile-content>
                                    <v-list-tile-title>Auto-add widgets</v-list-tile-title>
                                    <v-list-tile-sub-title>Automatically add home screen widgets</v-list-tile-sub-title>
                                </v-list-tile-content>
                            </v-list-tile>
                        </v-list>
                    </v-card>
                </v-dialog>
            </v-layout>
        </template>
        <!---------------------------------------------------->
    </v-container>
</template>

<script>
    import EditModal from "./EditModal.vue";
    import {mapGetters, mapState} from "vuex";

    export default {
        data() {
            return {
                headers: [
                    {
                        text: 'ID'
                    },
                    {
                        text: 'Фамилия'
                    },
                    {
                        text: 'Имя'
                    },
                    {
                        text: 'Отчество'
                    },
                    {
                        text: 'Action'
                    }
                ],
                aa: this.$store.getters.getprofile,
                person: {},
                dropdown_font: ['Arial', 'Calibri', 'Courier', 'Verdana'],
                date: '',
                BirthDateMenuVisibility: false,
                // date:  this.person.birthday.toISOString().substr(0, 10),
                // new Date().toISOString().substr(0, 10),
                menu2: false,
                dialog: false,
                formTitle: '',
                dialogFullScreen: false,
                notifications: false,
                sound: true,
                widgets: false
            }
        },
        methods: {
            deletePerson(item, index) {
                if (confirm('Удалить дело?')) {
                    this.$store.dispatch('deletePersonAction', {item, index});
                }
            },
            save() {
                this.dialogFullScreen = false
            },
            formatDate(date) {
                if (!date) return null

                const [year, month, day] = date.split('-')
                return `${day}/${month}/${year}`
            },
            loadPersonModal(id) {
                this.$router.push('/message/edit/' + id)
            },
            editPerson(id) {
                this.$store.dispatch('GET_personOneAction', id);
                this.person = this.$store.getters.getPersonOne;
                this.dialogFullScreen = true;
            }
        },
        components: {
            editDialog: EditModal
        },

        computed: {
            ...mapGetters(['listPersonsCount']),
            ...mapState({
                listPersons: state => state.listPerson.listPersons
            }),

            // GET_persons() {
            //     this.alert = true;
            //     return this.$store.getters.getPersons
            //     this.alert = false;
            // },
            profile() {
                return this.$store.getters.getprofile
            },
            loading() {
                return this.$store.getters.getProcessing
            },
            computedDateFormatted() {
                // this.date = this.person.birthday;
                console.log(this.date);
                return this.formatDate(this.date)
            },
            myDate() {
                this.date = this.person.birthday.toISOString().substr(0, 10)
                // this.date = new Date().toISOString().substr(0, 10)
                return new Date().toISOString().substr(0, 10)
            },
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
        },
    }
</script>

<style scoped>

</style>
