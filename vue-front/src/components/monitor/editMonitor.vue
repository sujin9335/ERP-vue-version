<template>
    <div
        ref="draggableDiv"
        :style="divStyle"

        :class="[{
                    'editMode' : editor,
                }]" 
        @mousedown="onMouseDown"
        
    >
        <div>{{ isIdCheck }}</div>
        <div>{{ data.monitor_title }}</div>
        <pie/>
        <div v-if="isIdCheck" class="resizer right" @mousedown="onResizeMouseDown('right', $event)"></div>
        <div v-if="isIdCheck" class="resizer bottom" @mousedown="onResizeMouseDown('bottom', $event)"></div>
        <div v-if="isIdCheck" class="resizer all" @mousedown="onResizeMouseDown('all', $event)"></div>
    </div>
</template>



<script setup lang="ts">
import pie from '@/highChart/pie.vue';
import { defineProps, ref, onMounted, watch } from 'vue';
import { useMonitorStore } from '@/stores/monitor';

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

//eidtor : 에디터모드, data : 모니터 데이터, selectedMonitor : 선택된 모니터(얘만 변경가능하게)
const props = defineProps<{ editor: boolean; data: Monitor; selectedMonitor: string | null; }> ();

const draggableDiv = ref<HTMLDivElement | null>(null); //div를 참조할 변수

const userMonitorStore = useMonitorStore();

const divStyle = ref({
    position: 'absolute',
    left: `${props.data.monitor_x}px`,
    top: `${props.data.monitor_y}px`,
    width: `${props.data.monitor_width}px`,
    height: `${props.data.monitor_hight}px`,
    // backgroundColor: 'lightgray',
    cursor: props.editor ? 'pointer' : ''
});

const isIdCheck = ref(false);

const startX = ref(0);
const startY = ref(0);
const startLeft = ref(0);
const startTop = ref(0);
const startWidth = ref(0);
const startHeight = ref(0);
const isResizing = ref(false);


const onMouseDown = (event: MouseEvent) => {
    // if(props.selectedMonitor == props.data.monitor_id) isIdCheck.value = true;

    if (!draggableDiv.value || !props.editor || !isIdCheck.value) return; 
    startX.value = event.clientX;
    startY.value = event.clientY;
    startLeft.value = draggableDiv.value.offsetLeft;
    startTop.value = draggableDiv.value.offsetTop;

    document.addEventListener('mousemove', onMouseMove);
    document.addEventListener('mouseup', onMouseUp);


};

const onMouseMove = (event: MouseEvent) => {
    if (!draggableDiv.value) return;
    if (isResizing.value) return; // 크기 조절 중이면 이동 중지

    const dx = event.clientX - startX.value;
    const dy = event.clientY - startY.value;

    const newLeft = startLeft.value + dx;
    const newTop = startTop.value + dy;

    divStyle.value = {
        ...divStyle.value,
        left: `${newLeft}px`,
        top: `${newTop}px`
    };
};

const onMouseUp = () => {
    document.removeEventListener('mousemove', onMouseMove);
    document.removeEventListener('mouseup', onMouseUp);
    isResizing.value = false;// 크기 조절 종료

    if (!draggableDiv.value) return;

};

const onResizeMouseDown = (direction: string, event: MouseEvent) => {
    event.stopPropagation(); // 부모 요소의 mousedown 이벤트 방지
    isResizing.value = true;

    startX.value = event.clientX;
    startY.value = event.clientY;
    startWidth.value = draggableDiv.value ? draggableDiv.value.clientWidth : 0;
    startHeight.value = draggableDiv.value ? draggableDiv.value.clientHeight : 0;

    const onResizeMouseMove = (event: MouseEvent) => {
        if (!draggableDiv.value) return;

        const dx = event.clientX - startX.value;
        const dy = event.clientY - startY.value;

        if (direction === 'right' || direction === 'all') {
        const newWidth = startWidth.value + dx;
        divStyle.value.width = `${newWidth}px`;
        }
        
        if (direction === 'bottom' || direction === 'all') {
        const newHeight = startHeight.value + dy;
        divStyle.value.height = `${newHeight}px`;
        }
    };

    const onResizeMouseUp = () => {
        document.removeEventListener('mousemove', onResizeMouseMove);
        document.removeEventListener('mouseup', onResizeMouseUp);
        isResizing.value = false; // 크기 조절 종료
    };

    document.addEventListener('mousemove', onResizeMouseMove);
    document.addEventListener('mouseup', onResizeMouseUp);
};

watch( //선택 id 변화 체크
    () => props.selectedMonitor,
    (newSelectedMonitor) => {
        isIdCheck.value = props.selectedMonitor == props.data.monitor_id;
    }
);



watch(() => props.editor, async (newVal, oldVal) => {
    if (!newVal) {
        const style = window.getComputedStyle(draggableDiv.value);
        const id = props.data.monitor_id;
        const x = parseInt(style.left, 10);
        const y = parseInt(style.top, 10);
        const width = parseInt(style.width, 10);
        const height = parseInt(style.height, 10);
        const monitorData = {
                "id": id,
                "x": x, 
                "y": y, 
                "width": width, 
                "height":height 
            };
        console.log(`x: ${x}, y: ${y}, width: ${width}, height: ${height}`);
        await userMonitorStore.monitorCoordinateUpdate(monitorData);

    }
    
});

</script>

<style scoped>

.selectTagStyle {
    border: 2px solid orangered;
    cursor: move;
}

.editMode {
    border: 2px solid blue;
    cursor: pointer;
}

.editMode:hover {
    box-shadow: 10px 10px 15px rgba(0, 0, 0, 0.5);
}

.resizer {
    position: absolute;
    width: 10px;
    height: 10px;
    background: #000;
    bottom: 0;
    right: 0;
    cursor: nwse-resize;
    
}

.resizer.all {
    bottom: -2px;
    right: -2px;
    z-index: 1;
}

.resizer.bottom {
    width: 100%;
    height: 4px;
    background: none;
    bottom: -2px;
    left: 0;
    cursor: ns-resize;
}

.resizer.right {
    width: 4px;
    height: 100%;
    background: none;
    top: 0;
    right: -2px;
    cursor: ew-resize;
}
</style>