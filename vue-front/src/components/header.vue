
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
                ">구성</a></li>
            
        </ul>

        <div class="btn-group" role="group">
            <div data-bs-toggle="dropdown" aria-expanded="false" style="cursor: pointer;">
                <i class="bi bi-gear"></i>
            </div>
            <div class="dropdown-menu text-center p-3">
                <button 
                    @click="toggleModal"
                    type="button" class="btn btn-outline-secondary mb-2">구성</button>
                <button 
                    @click="toggleEditMode"
                    type="button" 
                    :class="[
                        { 'active': monitorStore.isEdit },
                        { 'btn btn-outline-secondary': true}
                    ]"
                    >편집모드</button>
            </div>
        </div>
        

        
        <!-- 로그아웃 버튼 표시 -->
        <div class="col-md-3 text-end">
            
            

            <span>안녕하세요 {{ userName }}님</span>
            <button type="button" class="btn btn-outline-primary me-2" @click="authStore.logout()">Logout</button>
            <!-- <button type="button" class="btn btn-outline-primary me-2"
            onclick="location.href='/'">Login</button> -->
        </div>
        

        
        </header>
        <modal v-if="isModal" @close="toggleModal"/>
    </div>
</template>
<script setup lang="ts">
    import { ref, computed } from "vue";
    import { useAuthStore } from "@/stores/auth";
    import modal from '@/components/modals/contents.vue';
    import { useMonitorStore } from '@/stores/monitor';

    // 스토어 사용
    const authStore = useAuthStore();
    const monitorStore = useMonitorStore();

    const isModal = ref(false); //모달창 on off

    const userName = computed(() => authStore.loginInfo?.user_name || "게스트");
    const userAuth = computed(() => authStore.loginInfo?.user_auth || "게스트");

    const toggleModal = () => {
        isModal.value = !isModal.value;
    };

    const toggleEditMode = () => {
        monitorStore.toggleEditMode();
    };


</script>
<style scoped>
.active {
    color: white;
    background-color: green;
}
</style>