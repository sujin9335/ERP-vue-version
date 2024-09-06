<template>
    <div>
        <highcharts 
        :options="chart"
        style="width: 100%; height: 100%;"
        ></highcharts>
    </div>
</template>

<script setup lang="ts">
import { ref, defineProps, computed } from 'vue';

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

const props = defineProps<{data: Monitor; }> ();


const title = ref(props.data.monitor_title);
const content = ref(props.data.monitor_content);

const chartData = ref([{ name: '서울', y: 120 },
                    { name: '부산', y: 110 },
                    { name: '대구', y: 80 },
                    { name: '인천', y: 95 }]);
const drillData = ref([]);


const chart = computed(() => ({
            
    chart: {
            backgroundColor: 'transparent',
            type: content.value
        },
        credits: {
            enabled: false  // 우측 하단 로고 제거
        },
        title: {
            text: title.value
        },
        tooltip: {
            valueSuffix: '명'
        },

        plotOptions: {
            series: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: [{
                    enabled: true,
                    distance: 20
                }, {
                    enabled: true,
                    distance: -40,
                    // format: '{point.percentage:.1f}%',
                    formatter: function () {
                        // console.log(this)
                        if (this.point.drilldown) {
                            return this.point.percentage.toFixed(1)+"%";
                        } else {
                            return "ID: "+this.key;
                        }
                    },
                    style: {
                        fontSize: '1em',
                        textOutline: 'none',
                        opacity: 0.7
                    }
                }]
            }
        },
        exporting: {
            enabled: false // 우측 상단 메뉴를 비활성화합니다
        },
        series: [
            {
                name: '유저',
                colorByPoint: true,
                data: chartData.value
            }
        ],
        drilldown: {
            breadcrumbs: {
                position: {
                    align: 'center'
                }
            },
            series: drillData.value
        }
}));

</script>

<style scoped>
/* Add your component styles here */
</style>