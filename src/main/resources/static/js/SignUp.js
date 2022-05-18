    function isSame1() {
        
        if(document.getElementById('input2').value==document.getElementById('input3').value) {
           document.getElementById('same1').innerHTML='비밀번호가 일치합니다.';
           document.getElementById('same1').style.color='blue';
        }
        else if(document.getElementById('input2').value=='') {
			document.getElementById('same1').innerHTML='';
		}
        
        else {
           document.getElementById('same1').innerHTML='비밀번호가 일치하지 않습니다.';
           document.getElementById('same1').style.color='red';
        }
}
	function isSame2() {
        
        if(document.getElementById('input11').value==document.getElementById('input12').value) {
           document.getElementById('same2').innerHTML='비밀번호가 일치합니다.';
           document.getElementById('same2').style.color='blue';
        }
        else if(document.getElementById('input11').value=='') {
			document.getElementById('same2').innerHTML='';
		}
        else {
           document.getElementById('same2').innerHTML='비밀번호가 일치하지 않습니다.';
           document.getElementById('same2').style.color='red';
        }
}
	    