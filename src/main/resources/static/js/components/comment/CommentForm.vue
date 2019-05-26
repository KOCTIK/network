<template>
    <v-layout row class="px-3 mt-3">
        <v-text-field
                :label="$t('addComment')"
                :placeholder="$t('writeSomething')"
                v-model="text"
                @keyup.enter="save"
        />
        <v-btn @click="save">
            {{$t('add')}}
        </v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        name: "CommentForm",
        props: ['messageId'],
        data(){
            return{
                text:''
            }
        },
        methods:{
            ...mapActions(['addCommentAction']),
            async save(){
                await this.addCommentAction({
                    text: this.text,
                    message:{
                        id: this.messageId
                    }
                })

                this.text = ''
            }
        }
    }
</script>

<style scoped>

</style>