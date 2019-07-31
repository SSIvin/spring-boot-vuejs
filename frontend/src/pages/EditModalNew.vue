<template>
        <v-dialog v-model="dialog" max-width="500px">
            <v-btn icon class="mx-0" slot="activator">
                <!--@click="editItem(props.item)"-->
                <v-icon color="teal">edit</v-icon>
            </v-btn>
            <!--<v-btn slot="activator" color="primary" dark class="mb-2">New Item</v-btn>-->
            <v-card>
                <v-card-title>
                    <span class="headline">{{ formTitle }}</span>
                </v-card-title>
                <v-card-text>
                    <v-container grid-list-md>
                        <v-layout wrap>
                            <v-flex xs12 sm6 md4>
                                <v-text-field v-model="itemEdit.f" label="Фамилия"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                                <v-text-field v-model="itemEdit.i" label="Имя"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                                <v-text-field v-model="itemEdit.o" label="Отчество"></v-text-field>
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
</template>

<script>    
    export default {
        props: ['itemEdit'],
        data() {
            return {
                dialog: false,
                formTitle:'ЗАГОЛОВОК',
                f: this.itemEdit.f,
                i: this.itemEdit.i,
                o: this.itemEdit.o,
                methods: {

                },
            }
        }
    }
</script>

<style scoped>

</style>
