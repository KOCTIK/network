<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <user-link
                    :user="message.author"
                    size = "48"
            ></user-link>
            <div class="pt-3">
                {{message.text}}
            </div>

        </v-card-text>

        <v-card-actions>
            <v-btn value="Edit" @click="edit" small flat round>{{$t('edit')}}</v-btn>
            <v-btn icon @click="del" small>
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list
                :comments="message.comments"
                :message-id="message.id"
        ></comment-list>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'
    import CommentList from "../comment/CommentList.vue";
    import UserLink from "components/UserLink.vue";

    export default {
        components: {UserLink, CommentList },
        props: ['message', 'editMessage'],
        methods:{
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message);
            },
            del() {
               this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style>

</style>