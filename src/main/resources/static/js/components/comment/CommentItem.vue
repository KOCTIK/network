<template>
    <v-card flat class="pt-3 px-3">

            <user-link
                    :user="comment.author"
            ></user-link>
        <v-card-text class="ml-4">
            {{comment.text}}
        </v-card-text>
    </v-card>
</template>

<script>
    import UserLink from "components/UserLink.vue";
    export default {
        name: "CommentItem",
        components: {UserLink},
        props: ['comment']
    }
</script>

<style scoped>

</style>