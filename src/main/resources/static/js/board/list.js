// 아무거나?
// (function (){})()//익명의 함수를 바로호출
//
// function init() {//init 이름 가진 함수를 바로 호출
//
// }
// init();
(function () {
    'use strict'//에러 메세지를 콘솔에 다 띄우겠다.

    const boardListElem = document.querySelector('#board_list');
    const dataElem = document.querySelector('#data');

    const myUrl = new URL (window.location.href);
    const searchParams = myUrl.searchParams;
    const icategory = searchParams.get('icategory');

    const getList = () => {//일단 페이징 없이
        myFetch.get(`/ajax/board/${icategory}`, list => {
            console.log(list);
        });
    }

    getList();

    // console.log(dataElem.dataset.icategory);

    console.log(icategory);




})();