<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pensieve</title>

    <link rel="icon" type="image/ico" href="${path}/img/pensieve4.ico">
    <script src="${path}/js/jquery/jquery-3.7.1.js"></script>
<!--     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<%--     <link rel="stylesheet" href="${path}/css/bootstrap.css"> --%>
<link rel="stylesheet" href="/pensieve/css/themes/prism-okaidia.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${path}/css/common/login.css">

    
  </head>
<body>
<div id="container" class="container">
    <!-- FORM SECTION -->
    <div class="row">
      <!-- SIGN UP -->
      <div class="col align-items-center flex-col sign-up">
        <div class="form-wrapper align-items-center">
          <div class="form sign-up">
            <div class="input-group form-floating">
              <input type="text" name="join-username" placeholder="Username">
              <i class="fa fa-check" aria-hidden="true" style="color:#56cc9d;"></i>
              <i class="fa fa-times" aria-hidden="true" style="color:#ff7851;"></i>
              <label>Username</label>
            </div>
            <div class="feedback"></div>
            <div class="input-group form-floating">
              <input type="password" name="join-password"  id="password1" placeholder="Password">
              <i class="fa fa-check" aria-hidden="true" style="color:#56cc9d;"></i>
              <i class="fa fa-times" aria-hidden="true" style="color:#ff7851;"></i>
              <label>Password</label>
            </div>
            <div class="feedback"></div>
            <div class="input-group form-floating">
              <input type="password" id="password2" placeholder="Confirm password">
              <i class="fa fa-check" aria-hidden="true" style="color:#56cc9d;"></i>
              <i class="fa fa-times" aria-hidden="true" style="color:#ff7851;"></i>
              <label>Confirm password</label>
            </div>
            <div class="feedback" style="margin-bottom: 1px;"></div>
            <button type="button" id="btnJoin" disabled>
              Sign up
            </button>
            <p>
              <span>
                계정이 있으신가요?
              </span>
              <b onclick="toggle()" class="pointer">
                로그인
              </b>
            </p>
          </div>
        </div>
      
      </div>
      <!-- END SIGN UP -->
      <!-- SIGN IN -->
      <div class="col align-items-center flex-col sign-in">
        <div class="form-wrapper align-items-center">
          <div class="form sign-in">
          <form id="loginForm" action="${path}/login" method="POST">
            <div class="input-group form-floating">
              <input type="text" name="login-username" placeholder="Username">
              <i class="fa fa-check" aria-hidden="true" style="color:#56cc9d;"></i>
              <i class="fa fa-times" aria-hidden="true" style="color:#ff7851;"></i>
              <label>Username</label>
            </div>
            <div class="input-group form-floating">
              <input type="password" name="login-password" placeholder="Password">
              <i class="fa fa-check" aria-hidden="true" style="color:#56cc9d;"></i>
              <i class="fa fa-times" aria-hidden="true" style="color:#ff7851;"></i>
              <label>Password</label>
            </div>
            <button id="btnLogin" type="button"  disabled>
              Sign in
            </button>
            <p>
              <span>
                계정이 없으시다구욧?
              </span>
              <b onclick="toggle()" class="pointer">
                가입
              </b>
            </p>
            </form>
          </div>
        </div>
        <div class="form-wrapper">
    
        </div>
      </div>
      <!-- END SIGN IN -->
    </div>
    <!-- END FORM SECTION -->
    <!-- CONTENT SECTION -->
    <div class="row content-row">
      <!-- SIGN IN CONTENT -->
      <div class="col align-items-center flex-col">
        <div class="text sign-in">
          <h2>
            Welcome
          </h2>
  
        </div>
        <div class="img sign-in">
    
        </div>
      </div>
      <!-- END SIGN IN CONTENT -->
      <!-- SIGN UP CONTENT -->
      <div class="col align-items-center flex-col">
        <div class="img sign-up">
        
        </div>
        <div class="text sign-up">
          <h2>
            Join with us
          </h2>
  
        </div>
      </div>
      <!-- END SIGN UP CONTENT -->
    </div>
    <!-- END CONTENT SECTION -->
  </div>
   <script>
   let container = document.getElementById('container')

   toggle = () => {
     container.classList.toggle('sign-in')
     container.classList.toggle('sign-up')

     $.each($('input'),(idx, ele)=>
     {
         $(ele).val('');
         validateInput($(ele));
         $('#btnJoin').prop('disabled', true);
         $('#btnLogin').prop('disabled', true);  
     }); 
   }

   setTimeout(() => {
     container.classList.add('sign-in')
   }, 200)
   
// 입력할때, focusout 될때 유효값을 검사한다.
$('input').on('keyup focusout',(event)=>
{
    let target    = $(event.target);
    validateInput(target);
    
    let formDiv = target.parents('div.form').attr('class');
    
    //로그인 화면일때
    if(formDiv.includes('sign-in'))
    {
        if($('.form.sign-in input.is-valid').length === 2)
        {
            $('#btnLogin').prop('disabled', false);  
        }
        else
        {
            $('#btnLogin').prop('disabled', true);  
        }
    
    }
    //회원가입 화면일때
    else if(formDiv.includes('sign-up'))
    {
        if($('.form.sign-up input.is-valid').length === 3)
        {
            $('#btnJoin').prop('disabled', false);  
        }
        else
        {
            $('#btnJoin').prop('disabled', true);  
        }
    }
});

// 로그인 버튼을 눌렀을떄
$('#btnLogin').on('click',(event)=>
{
	//전송하기전에 잘못된 입력이 있을 경우를 체크한다
    if($('.form.sign-in input.is-valid').length !== 2)
    {
        $('#btnLogin').prop('disabled', true);  

        $.each($('.form.sign-in input'),(idx, ele)=>
        {
            validateInput($(ele));
            if(!$(ele).hasClass('is-valid'))
            {
                $(ele).focus();
                return false;
            }
        }); 
    }
	
	$('#loginForm').submit();
});
// 회원가입 버튼을 눌렀을떄
$('#btnJoin').on('click',(event)=>
{
    //전송하기전에 잘못된 입력이 있을 경우를 체크한다
    if($('.form.sign-up input.is-valid').length !== 3)
    {
        $('#btnJoin').prop('disabled', true);  

        $.each($('.form.sign-up input'),(idx, ele)=>
        {
            validateInput($(ele));
            if(!$(ele).hasClass('is-valid'))
            {
                $(ele).focus();
                return false;
            }
        }); 
    }
    
    let memberId = $('.form.sign-up input[name=join-username]').val();
    let memberPw = $('.form.sign-up input[name=join-password]').val();

    let data = {
            memberId,
            memberPw,
    };
    

    $.ajax({
        url         : '${path}/joinMember',
        type        : 'POST',
        dataType    : 'json',
        contentType : 'application/json;charset=utf-8',
        data        : JSON.stringify(data),
        success     : (data)=>
        {
        	console.log(data);
            if(data.result === 0)
            {
                alert('회원 등록 중 에러가 발생했습니다.');
            }
            else
            {
                alert('가입이 완료되었습니다.');
                
                $.each($('.form.sign-up input'),(idx, ele)=>
                {
                    $(ele).val('');
                    validateInput($(ele));
                    $('#btnJoin').prop('disabled', true);  
                }); 
                toggle();
            }
        },
        error : (data)=>
        {
            console.log(data)
            alert('회원 등록 중 에러가 발생했습니다.');
        }
    });
    
    
    
});
// 입력된 값이 올바른 값인지 검사한다.
function validateInput(target)
{
    let inputType = target.attr('type');
    let inputVal  = target.val();

    if(inputVal.length === 0)
    {
        showFeedback(target, 'init', '');
        return false;
    }
    
    if(inputType === 'text')
    {
        let userNameRegexp = /[a-z]{6,}/gi;
        
        if(!userNameRegexp.test(inputVal))
        {
            showFeedback(target, 'invalid', '6자 이상 영문만 사용바랍니다.');
            target.val(inputVal.replace(/[^a-z]/gi, ''));
            return false;
        }
        else if(inputVal.includes('admin') || inputVal.includes('sys'))
        {
            showFeedback(target, 'invalid', '사용불가 단어입니다.');
            target.val(inputVal.replace(/[^a-z]/gi, ''));
            return false;
        }
        else
        {
               duplicationCheck(target, inputVal);
        }
        return false;
    }
    if(inputType === 'password')
    {
        let password1 = $('#password1');
        let password2 = $('#password2');
        
        showFeedback(target, 'valid', '사용가능한 패스워드입니다.');

        if(inputVal.length < 4)
        {
            showFeedback(target, 'invalid', '4자 이상 입력해주세요');
        }
        else if(password1.val() !== password2.val() && password2.val()!== '')
        {
            showFeedback(password2, 'invalid', '패스워드가 일치하지 않습니다.');
        }
        else if(password2.val()!== '' && password1.hasClass('is-valid'))
        {
            showFeedback(password2, 'valid', '사용가능한 패스워드입니다.');
        }
        return false;
    }
}
// 입력된 값의 검사 결과 값을 표시한다.
function showFeedback(ele, eval, showText)
{
   if(eval === 'valid')
   {
       ele.removeClass('is-valid');
       ele.removeClass('is-invalid');
        ele.addClass('is-valid');
       ele.parents('div.input-group').next('.feedback').text(showText);
   }
    if(eval === 'invalid')
    {
        ele.removeClass('is-valid');
        ele.removeClass('is-invalid');
        ele.addClass('is-invalid');
        ele.parents('div.input-group').next('.feedback').text(showText);
    }

    if(eval === 'init')
    {
        ele.removeClass('is-valid');
        ele.removeClass('is-invalid');
        ele.parents('div.input-group').next('.feedback').text(showText);
    }
    
    if(showText !== '')
    {
        ele.parents('div.input-group').next('.feedback').show();
    }
    else
    {
        ele.parents('div.input-group').next('.feedback').hide();
    }
}
// 유저네임(memberId) 중복검사 Ajax
function duplicationCheck(target, inputVal)
{
    let memberId = $('.form.sign-up input[name=join-username]').val();
    let data = {memberId}; 
    
    $.ajax({
        url         : '${path}/idCheck',
        type        : 'GET',
        dataType    : 'json',
//         contentType : 'application/json;charset=utf-8',
//         data        : JSON.stringify(data),
        data        : data,
        success     : (data)=>
        {
            if(data.result)
            {
                showFeedback(target, 'invalid', '이미 존재하는 유저명입니다.');
                target.val(inputVal.replace(/[^a-z]/gi, ''));
            }
            else
            {
                showFeedback(target, 'valid', '사용가능한 유저명입니다.');
            }
        },
        error : (data)=>
        {
            console.log(data)
            showFeedback(target, 'invalid', '중복확인중 에러가 발생했습니다.');
            target.val(inputVal.replace(/[^a-z]/gi, ''));
        }
    });
}
   </script>
</body>
</html>
