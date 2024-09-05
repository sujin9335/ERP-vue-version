<template>
    <div>
        <div>
            <button @click="toggleModal" class="btn btn-secondary py-2 mt-2">구성</button>
            <modal v-if="isModal" @close="toggleModal"/>
            <button @click="toggleEditMode" class="btn btn-outline-primary py-2 mt-2">편집모드</button>
        </div>
        <div>
            <editMonitor v-for="monitor in monitorList" 
                :key="monitor.monitor_id"
                :data="monitor" 
                :editor="isEditMode"
                :selectedMonitor="selectedMonitor"
                :class="{ 'selected': isEditMode && selectedMonitor === monitor.monitor_id }" 
                @click="selectMonitor(monitor)"
            />
        </div>
    </div>


</template>
<script setup lang="ts">
import { onMounted, ref, watch } from "vue";
import modal from '@/components/modals/contents.vue';
import { useMonitorStore } from '@/stores/monitor';
import editMonitor from '@/components/monitor/editMonitor.vue';

const monitorStore = useMonitorStore();
const monitorList = ref([]);//모니터 리스트 받아올 변수

const isModal = ref(false); //모달창 on off
const isEditMode = ref(false); //에디터모드 on off

const selectedMonitor = ref(null);//선택 모니터 (테두리 표시)

const selectMonitor = (monitor) => { //선택된 모니터 입력
    if (!isEditMode.value) return;
    selectedMonitor.value = monitor.monitor_id;
};


const toggleModal = () => {
    isModal.value = !isModal.value;
};

const toggleEditMode = () => {
    isEditMode.value = !isEditMode.value;
    if (!isEditMode.value) {
        selectedMonitor.value = null;
    }
};

onMounted( async () => {
    // const monitorList = 
    await monitorStore.getMonitorList();
    monitorList.value = monitorStore.monitorList;
    console.log('mounted');
});




</script>
<style scoped>
.selected {
  border: 2px solid red; /* 원하는 테두리 색상으로 변경 */
}    



</style>