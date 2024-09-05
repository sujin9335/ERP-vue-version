
<template lang="">
    <div class="container">
        <header
        class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="col-md-3 mb-2 mb-md-0">
            <a href="/home" class="d-inline-flex link-body-emphasis text-decoration-none">
                home
            </a>
        </div>
        
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0" id="menu">
            <li><a href="/board"  id="menu_board" 
                :class="[
                    { 'active': $route.path === '/board' },
                    {'nav-link px-2': true}
                ]
                ">게시판</a></li>
            <li><a href="/user" id="menu_user" :class="[
                    { 'active': $route.path === '/user' },
                    {'nav-link px-2': true}
                ]
                ">유저관리</a></li>
            <li><a href="/group" id="menu_group" :class="[
                    { 'active': $route.path === '/group' },
                    {'nav-link px-2': true}
                ]
                ">그룹관리</a></li>
            <!-- <li><a href="/test" id="menu_group" :class="[
                    { 'active': $route.path === '/test' },
                    {'nav-link px-2': true}
                ]
                ">Test</a></li> -->
            <li><a href="/monitor" id="menu_group" :class="[
                    { 'active': $route.path === '/monitor' },
                    {'nav-link px-2': true}
                ]
                ">모니터</a></li>
            <li><a href="/test3" id="menu_group" :class="[
                    { 'active': $route.path === '/test3' },
                    {'nav-link px-2': true}
                ]
                ">구성</a></li>
        </ul>
        

        
        <!-- 로그아웃 버튼 표시 -->
        <div class="col-md-3 text-end">
            

            <span>안녕하세요 {{ userName }}님</span>
            <button type="button" class="btn btn-outline-primary me-2" @click="authStore.logout()">Logout</button>
            <!-- <button type="button" class="btn btn-outline-primary me-2"
            onclick="location.href='/'">Login</button> -->
        </div>
        

        
        </header>
    </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import { useAuthStore } from "@/stores/auth";

export default defineComponent({
    data() {
        return {
            authStore: useAuthStore(),
        };
    },
    computed: {
        userName() {
            return this.authStore.loginInfo?.user_name || "게스트";
        },
    },
});
</script>
<style scoped>
.active {
    color: white;
    background-color: green;
}
</style>