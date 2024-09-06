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
                :setStyle="setStyle"
                :class="{ 'selected': isEditMode && selectedMonitor === monitor.monitor_id,}" 
                @click="selectMonitor(monitor)"
                @update="updateStyle"
            />
        </div>
        <div
            :style="{ display: selectedMonitor !== null ? 'block' : 'none' }"
            class="controls container text-center">
            <div class="row">
                
                <div class="col-5">
                    <div class="row">
                        <div class="row row-cols-2">
                            <div class="col border">가로</div>
                            <div class="col border">
                                <input 
                                    type="number"
                                    v-model.number="divWidth"
                                    @input="inputStyle"
                                /> 
                            </div>
                            <div class="col border">세로</div>
                            <div class="col border">
                                <input 
                                    type="number"
                                    v-model.number="divHeight"
                                    @input="inputStyle"
                                /> 
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-5">
                    <div class="row">
                        <div class="row row-cols-2">
                            <div class="col border">x축</div>
                            <div class="col border">
                                <input 
                                    type="number"
                                    v-model.number="divX"
                                    @input="inputStyle"
                                />     
                            </div>
                            <div class="col border">y축</div>
                            <div class="col border">
                                <input 
                                    type="number"
                                    v-model.number="divY"
                                    @input="inputStyle"
                                />     
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <button>삭제</button>
                </div>
            </div>
        </div>
    </div>


</template>
<script setup lang="ts">
import { onMounted, ref, watch } from "vue";
import modal from '@/components/modals/contents.vue';
import { useMonitorStore } from '@/stores/monitor';
import editMonitor from '@/components/monitor/editMonitor.vue';

interface Monitor {
    monitor_id: string;
    monitor_content: string;
    monitor_title: string;
    monitor_x: number;
    monitor_y: number;
    monitor_width: number;
    monitor_hight: number;
    monitor_date: string;
}

const monitorStore = useMonitorStore();
const monitorList = ref<Monitor[]>([]);//모니터 리스트 받아올 변수

//모니터 정보
const divId = ref('');
const divWidth = ref(0);
const divHeight = ref(0);
const divX = ref(0);    
const divY = ref(0);

//인풋입력 설정할 스타일
const setStyle = ref();

const isModal = ref(false); //모달창 on off
const isEditMode = ref(false); //에디터모드 on off

const selectedMonitor = ref<string | null>(null);//선택 모니터 (테두리 표시)

const selectMonitor = (monitor: Monitor) => { //선택된 모니터 입력
    if (!isEditMode.value) return;
    selectedMonitor.value = monitor.monitor_id;
};

//input 태그 입력시 실행
const inputStyle = () => {
    const style = {
        id: divId.value,
        width: divWidth.value,
        height: divHeight.value,
        x: divX.value,
        y: divY.value,
    };

    setStyle.value = style;

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

//가로 길이 변경
const updateStyle = (newStyle: any) => {
    console.log(newStyle);
    divId.value = newStyle.id;
    divWidth.value = newStyle.width;
    divHeight.value = newStyle.height;
    divX.value = newStyle.x;
    divY.value = newStyle.y;

};

//모니터 리스트 받아오기
onMounted( async () => {
    // const monitorList = 
    await monitorStore.getMonitorList();
    monitorList.value = monitorStore.monitorList;
    console.log('mounted');
});




</script>
<style scoped>
.selected {
    border: 2px dashed red; /* 원하는 테두리 색상으로 변경 */
    box-sizing: border-box;
}    
.controls {
    border : 1px solid black;
    position: absolute;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
    gap: 10px;
    background-color: rgba(128, 128, 128, 0.5);
    width: 30%
}

input {
    width: 100%;
}



</style>