console.log('00000000000000000000000000');

async function removeCommentToServer(uuid) {
    console.log(uuid);
    try {
        const url = '/board/file/'+uuid;
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


document.addEventListener('click', (e) => {
    console.log(e.target);
    
    

    if (e.target.classList.contains('file-x')) {
        console.log("삭제버튼 클릭~!!");
        console.log('22222222222222');
    let div = e.target.closest('button');
    let uuid = div.dataset.uuid;
    console.log(uuid+"1");
    console.log('333333333333333333');
    removeCommentToServer(uuid).then(result => {
        if (result == 1) {
            alert('이미지 삭제 성공~!!');
            e.target.closest('li').remove()
            location.reload();
        }else{
            alert('파일삭제 실패');
        }
    })

}

})


















