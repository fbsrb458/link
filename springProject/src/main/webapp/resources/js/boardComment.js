console.log(bnoVal);

//등록
//댓글 등록 버튼에 이벤트 등록
document.getElementById('cmtPostBtn').addEventListener('click', ()=> {
    const cmtText = document.getElementById('cmtText').value;
    //span : cmtWriter
    const cmtWriter = document.getElementById('cmtWriter').innerText;
    console.log(cmtText);
    console.log(cmtWriter);

    if (cmtText==""||cmtText==null) {
        alert("댓글을 입력해 주세요");
        document.getElementById('cmtText').focus;
        return false;
    } else {
        let cmtData ={
            bno : bnoVal,
            writer : cmtWriter,
            content : cmtText
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result =>{
            console.log(result);
            //isOk 확인
            if (result==1) {
                alert("댓글 등록 성공");
                getCommentList(bnoVal) //다시 가져와서 뿌리기
                document.getElementById('cmtText').value = "";
            }
        })
    }
})

//댓글 데이터 서버 전송
async function postCommentToServer(cmtData) {
    try {
        const url = "/comment/post"
        const config = {
            method: "post",
            headers:{
                'content-type':'application/json;charset=UTF-8'
            },
            // 요청 본문 json 문자열
            body:JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config)
        const result = await resp.text(); //isOk
        return result;
    } catch (err) {
        console.log(err);
    }
}


//댓글 리스트 가져오기
//페이징 값도 가져오기
async function speadCommentListFromServer(bno, page){
    try {
        //받은것 (데이터)
        const resp = await fetch('/comment/'+bno+'/'+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//댓글뿌리기
//무조건 첨음 뿌릴 떄는 첫페이지 값을 뿌려야함.
//page 1로 지정
function getCommentList(bno, page=1){
    //speadCommentListFromServer 가져온 댓글 뿌리기
    speadCommentListFromServer(bno, page).then(result =>{
        console.log(result); // ph 객체 (pgvo, totalCount, cmtList)
        //화면에 뿌리기
        let ul = document.getElementById('cmtlistArea');
        if (result.cmtList.length > 0) {
        //다시 댓글을 뿌릴 때 기존값 삭제 1page일 경우만
        if (page==1) {
            ul.innerHTML = ""; 
        }
            for(let cvo of result.cmtList){
                let li = `<li data-cno="${cvo.cno}"><div>`;
                //let li = `<li data-cno="${cvo.cno}"><div>`;
                li += `<div class="fw-bold">${cvo.writer}</div>`;
                li +=`<input type="text"  value="${cvo.content}"></div>`;
                li +=`<span>${cvo.modAt}</span>`;
            
                    li +=`<button type="button" class="modBtn" data-bs-toggle="modal" data-bs-target="#myModal">%</button>`;
                    li +=`<button type="button" class="delBtn">X</button>`;
                
                li += `</li>`
                ul.innerHTML +=li;
                //if(아이디랑 작성자가 일치한다면...){
                // }

            }
            //댓글 페이징 코드
            let moreBtn = document.getElementById('moreBtn');
            `console.log(moreBtn);`
            //DB에서 pgvo + list 값을 같이 가져와야 해당 값을 줄수 있음.
            if (result.pgvo.pageNo < result.endPage || result.next) {
                moreBtn.style.visibility = 'visible'; //버튼 표시
                moreBtn.dataset.page = page + 1;
                console.log("11111111111111111111");
            }else {
                moreBtn.style.visibility = 'hidden'; //버튼 숨김
            }
            

        }else{
            let li = `<li>Comment List Empty</li>`;
            ul.innerHTML =li;
        }

    })
}


//삭제 
async function removeCommentToServer(cno){
    try {
        const url = '/comment/'+cno;
        const config = {
            method : 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(err);
    }
}

document.addEventListener('click', (e) =>{
    console.log(e.target);
    // if (e.target.classList.contains('modBtn')) { //mod와 일치하면 수정
    //     //수정작업
    //     console.log("수정버튼 클릭~!!");
    //     //closest : 나와 가장 가까운 li을 찾기
    //     let li = e.target.closest('li');
    //     let cnoVal = li.dataset.cno;
    //     let textContent = li.querySelector('#cmtTextMod').value;
    //     // let writerval = li.d
    //     console.log("cnt / content"+cnoVal +" / "+ textContent);
        
    //     let cmtModDate = {
    //         cno : cnoVal,
    //         content : textContent
    //     };
    //     console.log(cmtModDate);
    //     //서버연결
    //     editCommentToServer(cmtModDate).then(result =>{
    //         if(result==1){
    //             alert('댓글 수정 성공~!!');
    //         }
    //         getCommentList(bnoVal);
    //     })

    // }else 
    if(e.target.classList.contains('delBtn')){
        //삭제작업
        console.log("삭제버튼 클릭~!!");
        let li = e.target.closest('li');
        let cno = li.dataset.cno;
    
        removeCommentToServer(cno).then(result =>{
            if (result == 1) {
                alert('댓글 삭제 성공~!!!')
                
            }
            getCommentList(bnoVal);
        })


    }else if(e.target.classList.contains('modBtn')) {
        //모달창의 input 값 가져오기
        let li = e.target.closest('li');
        //nextSibling() : 같은 부모의 다음 형제 객체를 변환
        let cmtText = li.querySelector('.fw-bold').nextSibling;
        console.log(cmtText);
        //기존내용 모달창에 반영 (수정편하게 하기위해...)
        document.getElementById('cmtTextMod').value = cmtText.value;
        //cmtModBtn에 data-cno 달기
        document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);
    }else if(e.target.id == 'cmtModBtn') {
        let cmtDataMod ={
            cno : e.target.dataset.cno,
            content : document.getElementById('cmtTextMod').value
        };
        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result =>{
            if (parseInt(result)) {
                //모달창 닫기
                document.querySelector('btn-close').click();
            if (result == 1) {
                alert('댓글 수정 성공~!!');
            }
            getCommentList(bnoVal);
            
            }
        })
    }else if(e.target.id == 'moreBtn'){
        getCommentList(bnoVal, parseInt(e.target.dataset.page));
        console.log("왜 없어짐!!");
    }
})

//수정
 async function editCommentToServer(cmtDataMod){
    try {
        const url = '/comment/'+cmtDataMod.cno;
        const config = {
            method: 'put',
            headers: {
                'Content-Type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
    } catch (error) {
        console.log(err);
    }
 }
