import Vue from 'vue'
import VueI18n from 'vue-i18n'
// import en from '../langs/en'

Vue.use(VueI18n)

export const i18n = new VueI18n({
    locale: 'en',
    fallbackLocale: 'pl',
    messages:{
        en:{
            authorisation:'It is required authorisation through',
            message:'message',
            newMessage:'New message',
            writeSomething:'Write Something',
            save:'SAVE',
            edit:'EDIT',
            comments:'Comments',
            addComment:'Add comment',
            add:'ADD',
            userProfile:'User profile',
            subscribe:'Subscribe',
            unsubscribe:'Unsubscribe'
        },
        pl:{
            authorisation:'Wymagana jest autoryzacja przez',
            message:'wiadomość',
            newMessage:'Nowa wiadomość',
            writeSomething:'Napisz coś',
            save:'ZAPISAĆ',
            edit:'EDYTOWAĆ',
            comments:'Komentarze',
            addComment:'Dodaj komentarz',
            add:'DODAJ',
            userProfile:'Profil użytkownika',
            subscribe:'Podpisać',
            unsubscribe:'Anuluj'
        },
        uk:{
            authorisation:'Потрібна авторизація через',
            message:'повідомлення',
            newMessage:'Нове повідомлення',
            writeSomething:'Напишіть щось',
            save:'ЗБЕРЕГТИ',
            edit:'РЕДАГУВАТИ',
            comments:'Коментарі',
            addComment:'Додати коментар',
            add:'ДОДАТИ',
            userProfile:'Профіль користувача',
            subscribe:'Підписатися',
            unsubscribe:'Скасувати'

        }
    }
})