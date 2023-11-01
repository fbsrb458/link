console.log("asgadsghb");
// 트리거 버튼 처리
document.getElementById('trigger').addEventListener('click', ()=>{ //trigger id을 addEventListener 감싸서 보냄
    document.getElementById('files').click();
})


//실행파일, 이미지 파일에 대한 정규표현식 작성
const regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$") //실행파일이 막기 = 이유 악성코들을 막기 위해 예)트로이 목마같은거
const regExpImg = new RegExp("\.(jpg|jpeg|prg|gif)$"); //이미지 파일만
const maxSize = 1024*1024*20; //파일 최대 사이즈

function fileValidation(fileName, fileSize) { //파일에 대한 이름과 사이즈을 확인
    if (!regExpImg.test(fileName)) {
        return 0;
    }else if (regExp.test(fileName)) {
        return 0;
    }else if(fileSize > maxSize) {
        return 0;
    }else {
        return 1;
    }
    
}

document.addEventListener('change', (e) =>{ // 도큐먼트에 addEven을 덮으고 change뭔가 바끼면 객체을 보냄
    if (e.target.id == 'files') {
        //파일을 다시 추가할 때는 버튼 상태를 원래대로 변경해야함
        document.getElementById('regBtn').disabled = false;

        //input file element에 저장된 file의 정보를 가져오는 property
        const fileObj = document.getElementById('files').files;
        console.log(fileObj);

        //첨부파일에 대한 정보들 filezone 에 기록
        let div = document.getElementById('fileZone');
        //기존 값이 있으면 삭제
        div.innerHTML="";
        //ul => li로 첨부파일 추가
        //<ul class="list-group list-group-flush">
        //<li class="list-group-item">An item</li>
        let isOk = 1; //여러파일이 모두 검증에 통과해야 하기 때문에 * 로 각 파일마다 통과여부 확인
        let ul = `<ul class="list-group list-group-flush">`;
            for(let file of fileObj){
                let vaildResult = fileValidation(file.name, file.size); //0또는 1로 리턴
                isOk *= vaildResult;
                ul += `<li class="list-group-item">`;
                ul += `<div class="mb-3">`
                ul += `${vaildResult ? '<div class="mb-3 text-success">업로드 가능 </div>' : 
                '<div class="mb-3 text-danger" >업로드 불가능 </div>'}`;
                ul += `${file.name}</div>`;
                ul += `<span class="badge rounded-pill text-bg-${vaildResult ? 'success' : 'danger'}">${file.size}Bypes</span>`;
                ul +=`</li>`
                
            }
            ul +=`</ul>`;
            div.innerHTML = ul;

            if (isOk == 0) {
                document.getElementById('regBtn').disabled = true;
            }



    }
})




















