<template>
    <div>
        <v-avatar
                v-if="user && user.userpic"
                :size="`${size || 32}px`"
        >
            <img
                    :src="user.userpic"
                    :alt="user.name"
            >
        </v-avatar>

        <v-avatar
                v-else
                :size="`${size || 36}px`"
                color="indigo"
        >
            <v-icon dark>account_circle</v-icon>
        </v-avatar>

        <router-link
                :to="`/user/${user.id}`"
                class="px-3"
        >

            <span>{{userName}}</span>
        </router-link>

    </div>
</template>

<script>
    export default {
        name: "UserLink",
        props:['user', 'size'],
        computed:{
            userName(){
                return this.user ? this.user.name : 'unknown'
            }
        }
    }
</script>

<style scoped>

</style>