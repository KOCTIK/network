<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title> NETWORK</v-toolbar-title>
            <v-btn flat
                    v-if="profile"
                    :disabled="$route.path === '/'"
                    @click="showMessages">
                {{$t('message')}}
            </v-btn>

            <v-spacer></v-spacer>

            <v-menu offset-y>
                <template #activator="{ on: menu }">
                    <v-btn
                            flat
                            v-on="{ ...menu }"
                    >{{language}}</v-btn>
                </template>
                <v-list>

                    <v-list-tile
                            v-for="(item, index) in items"
                            :key="index"
                            @click="changeLanguage(item)"
                    >
<!--                        <flag iso="en" />-->
                        <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                    </v-list-tile>
                </v-list>
            </v-menu>

            <v-spacer></v-spacer>

            <v-btn flat
                   v-if="profile"
                   :disabled="$route.path === '/user'"
                   @click="showProfile">
                {{profile.name}}
            </v-btn>
            <v-btn v-if="profile" icon href="logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>


        </v-toolbar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>


<script>
    import {mapState, mapMutations} from 'vuex'
    import {addHandler} from "util/ws"

    export default {
        computed: mapState(['profile']),
        data(){
            return{
                language:"English",
                items: [
                    { title: 'English', locale:'en' },
                    { title: 'Polski', locale: 'pl' },
                    { title: 'Українська', locale: 'uk' }
                ]
            }
        },
        methods: {
            ... mapMutations([
                'addMessageMutation',
                'updateMessageMutation',
                'removeMessageMutation',
                'addCommentMutation'
            ]),
            showMessages(){
                this.$router.push('/')
            },
            showProfile(){
                this.$router.push('/user')
            },
            changeLanguage(item){
                // import(`../langs/${item.locale}.json`).then((data) => {
                //         this.$plugins.setLocaleMessage(item.locale, data)
                //     })
                // const data = await import(`../langs/${item.locale}.json`)
                // this.$plugins.setLocaleMessage(item.locale, data)
                this.$i18n.locale = item.locale
                this.language = item.title
            }
        },
        created(){
            addHandler(data => {
                if(data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                }else if(data.objectType === 'COMMENT') {
                    switch (data.eventType) {
                        case'CREATE':
                            this.addCommentMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                }else{
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                    }

            })
        },
        beforeMount() {
            if(!this.profile){
                this.$router.replace('/auth')
            }
        }

    }
</script>



<style>

</style>