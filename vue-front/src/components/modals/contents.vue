<template>
    <!-- <div v-if="isVisible" @click.self="closeModal"> -->
    <div >
        <div class="modal show" id="composition" data-bs-backdrop="static" data-bs-keyboard="true"
         aria-labelledby="staticBackdropLabel" aria-hidden="true" style="display: block;">
            <div class="modal-dialog modal-dialog-centered modal-custom">
                <div class="modal-content">

                    <div class="modal-body">
                        <div class="container" id="main">
                            <div class="row">
                                <div class=" text-end">
                                        <button id="norBtn" type="button" class="btn btn-success" @click="closeModal">닫기</button>
                                </div>
                                <div class="col-5 me-5 border hi-700" id="menu">
                                    <div class="my-2">구성 리스트</div>
                                    <div class="border hi-500 p-2 scroll" >
                                        <table class="table text-center" id="userTab">
                                            <thead>
                                                <tr>
                                                    <th class="col-2" scope="col">
                                                        <input class="form-check-input" type="checkbox" value=""
                                                            id="checkboxAll">
                                                    </th>
                                                    <th class="col-5" scope="col">타이틀</th>
                                                    <th class="col-5" scope="col">콘텐츠</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="text-end m-2">
                                        <button>삭제</button>
                                    </div>
                                </div>
                                <div class="col border hi-700" id="content">
                                    <!-- 메뉴 -->
                                    
                                    <!-- 내용 -->
                                    <div class="border m-2">
                                        <!-- <div class="container" style="display: none;"> -->
                                        <div class="container text-center" id="normalCon">
                                            <div class="row row-cols-2">
                                                <div class="col-2 my-2">콘텐츠</div>
                                                <div class="col-10 my-2">
                                                    <select >
                                                        <!-- <option v-for="">

                                                        </option> -->
                                                    </select>
                                                </div>
                                                <div class="col-2">타이틀</div>
                                                <div class="col-10 mb-2">
                                                    <input class="form-control" type="text" id="text">
                                                </div>
                                                <div class="col-2">데이터</div>
                                                <div class="col-10">
                                                    <textarea class="form-control" rows="17" id="info"></textarea>
                                                    <input type="hidden" id="id">
                                                </div>
                                            </div>
                                            <div class="text-end m-2">
                                                <button >등록</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>


export default {
    // props: { //부모에서 넘어온것
    //     isVisible: {
    //         type: Boolean, //타입설명
    //         required: true, //필수 인지
    //     },
    // },
    setup(props, { emit }) {

        const monitorData = [
            {

            }
        ];


        const closeModal = () => {
            emit('close');
        };

        return {
            closeModal,
        }
    }
}
</script>
<style lang="">
    
</style>