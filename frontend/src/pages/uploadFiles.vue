<template>
    <v-container>
        <h1>Загрузка файла</h1>
        <div class="container">
            <div class="large-12 medium-12 small-12 cell">
                <label>File
                    <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
                </label>
                <hr>
                <button v-on:click="submitFile()">Submit</button>
            </div>
        </div>
        <hr>
        <h1>Загрузка файлов</h1>
        <div class="container">
            <div class="progress" style="height:40px">
                <div class="progress-bar" role="progressbar" :style="{width:fileProgress + '%'}">{{fileCurrent}}%</div>
            </div>

            <div class="large-12 medium-12 small-12 cell">
                <label>File
                    <input type="file" name="files" ref="file" multiple="" @change="fileInputChange"/>
                </label>
                <hr>
            </div>
            <v-label class="indigo">
                {{finish}}
            </v-label>

            <div class="row">
                <div class="col-sm-6">
                    <h3 class="text-center">Файлы в очереди ({{filesOrder.length}})</h3>
                    <ul class="list-group">
                        <li class="list-group-item" v-for="file in filesOrder">
                            {{file.name}} : {{file.type}}

                        </li>
                    </ul>
                </div>
                <div class="col-sm-6">
                    <h3 class="text-center">Загруженные файлы ({{filesFinish.length}})</h3>
                    <ul class="list-group">
                        <li class="list-group-item" v-for="file in filesFinish">
                            {{file.name}} : {{file.type}}
                        </li>
                    </ul>

                </div>
            </div>

        </div>
    </v-container>
</template>

<script>
    import Axios from 'axios'

    export default {
        data() {
            return {
                message1: 'Hey!',
                message2: 'Hey!',
                message3: 'Hey!',
                message4: 'Hey!',
                file: '',
                filesOrder: [],
                filesFinish: [],
                fileProgress: 0,
                fileCurrent: '',
                finish:''
            }
        },
        methods: {
            handleFileUpload() {
                this.file = this.$refs.file.files[0];
                console.log(this.$refs.file.files[0].name)
            },
            async fileInputChange() {
                let files = Array.from(event.target.files);
                this.filesOrder = files.slice();

                for (let item of files) {
                   await this.uploadFile(item)
                }
                this.finish = 'Все файлы загружены.'
            },
            submitFile() {
                let formData = new FormData();
                formData.append('file', this.file);

                Axios.post('http://10.10.12.232:9001/api/addFile',
                    formData,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }
                ).then(function () {
                    console.log('SUCCESS!!');
                })
                    .catch(function () {
                        console.log('FAILURE!!');
                    });


            },
            async uploadFile(item) {

                let formData = new FormData();
                formData.append('file', item);

                await Axios.post('http://10.10.12.232:9001/api/addFile',
                    formData,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        },
                        onUploadProgress: (itemUpload) => {
                            this.fileProgress = Math.round((itemUpload.loaded / itemUpload.total) * 100)
                            this.fileCurrent = item.name + ' ' + this.fileProgress;
                        }
                    }
                ).then(response => {
                    this.fileProgress = 0;
                    this.fileCurrent = '';
                    this.filesFinish.push(item);
                    this.filesOrder.splice(item, 1);
                })
                    .catch(error=> {
                        console.log(error);
                    });
            }
        }

    }
</script>

<style scoped>

</style>
