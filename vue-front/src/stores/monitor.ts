import { acceptHMRUpdate, defineStore } from 'pinia';
import axios from "@/axios";

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

export const useMonitorStore = defineStore('monitor', {
    state: () => ({
        monitorList: [] as Monitor[],


    }),
    actions: {
        async getMonitorList() { //모니터링 리스트
            try {
                const result = await axios.post(
                    '/a/vmonitorList',
                );
                const monitorList = result.data;

                
                if (result.status === 200) {
                    console.log(monitorList.data);
                    this.monitorList = monitorList.data;
                    return monitorList

                }
            } catch (error) {
                alert("모니터링 리스트 통신에러");
            }
        },

        async monitorCoordinateUpdate(monitorData: any) { //모니터 좌표 업데이트
            try {
                const result = await axios.post(
                    '/a/vmonitorCoordinateUpdate',
                    {info: monitorData}
                );

                
                if (result.status === 200) {
                    console.log("완료");

                }
            } catch (error) {
                alert("모니터링 리스트 통신에러");
            }
        },



    },
});

// HMR 지원 설정(변경사항 바로적용)
if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useMonitorStore, import.meta.hot));
}