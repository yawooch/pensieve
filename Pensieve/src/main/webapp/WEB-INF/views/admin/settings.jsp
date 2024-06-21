<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
 
<link href="${path}/css/bootstrap-treeview.css"  rel="stylesheet"/>
<script src="${path}/js/bootstrap/bootstrap-treeview.js"></script>
<script>
let menuObj        = new Map();
let selectedObj    = false;
let isNodeSelected = false;
let firstTreeData = [{
    text         : 'Root',
    href         : '#parent1',
    menuId       : 0,
    menuUrl      : '/',
    menuName     : 'Root',
    menuLv       : 0,
    menuParentId : null,
    tags         : ['4']
    }
    ];
let dummyData   = [
{
text         : 'Root',
href         : '#parent1',
menuId       : '00',
menuUrl      : '/',
menuName     : 'Root',
menuLv       : 0,
menuParentId : null,
tags         : ['4'],
nodes        : [
    {
        text         : 'Main',
        href         : '/main/home',
        menuId       : '01',
        menuUrl      : '/main/home',
        menuName     : 'Main',
        menuLv       : 1,
        menuParentId : '00',
        tags         : ['4'],
        nodes        : [
        {
            text         : 'Text',
            href         : '/main/text',
            menuId       : '0101',
            menuUrl      : '/main/text',
            menuName     : 'Text',
            menuLv       : 2,
            menuParentId : '01',
            tags         : ['4']
        },
        {
            text         : 'Calendar',
            href         : '/main/calendar',
            menuId       : '0102',
            menuUrl      : '/main/calendar',
            menuName     : 'Calendar',
            menuLv       : 2,
            menuParentId : '01',
            tags         : ['4']
        },
        {
            text         : 'Timeline',
            href         : '/main/timeline',
            menuId       : '0103',
            menuUrl      : '/main/timeline',
            menuName     : 'Timeline',
            menuLv       : 2,
            menuParentId : '01',
            tags         : ['4']
        }
    ]},
    {
        text         : '우찬',
        href         : '/wc',
        menuId       : '02',
        menuUrl      : '/wc',
        menuName     : '우찬',
        menuLv       : 1,
        menuParentId : '00',
        tags         : ['4'],
        nodes        : [
        {
            text         : 'Text',
            href         : '/wc/text',
            menuId       : '0201',
            menuUrl      : '/wc/text',
            menuName     : 'Text',
            menuLv       : 2,
            menuParentId : '02',
            tags         : ['4']
        },
        {
            text         : 'Calendar',
            href         : '/wc/calendar',
            menuId       : '0202',
            menuUrl      : '/wc/calendar',
            menuName     : 'calendar',
            menuLv       : 2,
            menuParentId : '02',
            tags         : ['4']
        },
        {
            text         : 'Timeline',
            href         : '/wc/timeline',
            menuId       : '0204',
            menuUrl      : '/wc/timeline',
            menuName     : 'Timeline',
            menuLv       : 2,
            menuParentId : '02',
            tags         : ['4']
        }
    ]},
    {
        text         : '영우',
        href         : '/yw/text',
        menuId       : '03',
        menuUrl      : '/yw/text',
        menuName     : '영우',
        menuLv       : 1,
        menuParentId : '00',
        tags         : ['4'],
        nodes        : [
        {
            text         : 'Text',
            href         : '/yw/text',
            menuId       : '0301',
            menuUrl      : '/yw/text',
            menuName     : 'Text',
            menuLv       : 2,
            menuParentId : '03',
            tags         : ['4']
        },
        {
            text         : 'Calendar',
            href         : '/yw/calendar',
            menuId       : '0302',
            menuUrl      : '/yw/calendar',
            menuName     : 'Calendar',
            menuLv       : 2,
            menuParentId : '03',
            tags         : ['4']
        }
    ]},
    {
        text         : '주연',
        href         : '/jy/memo',
        menuId       : '04',
        menuUrl      : '/jy/memo',
        menuName     : '주연',
        menuLv       : 1,
        menuParentId : '00',
        tags         : ['4'],
        nodes        : [
        {
            text         : 'Memo',
            href         : '/jy/memo',
            menuId       : '0401',
            menuUrl      : '/jy/memo',
            menuName     : 'Memo',
            menuLv       : 2,
            menuParentId : '04',
            tags         : ['4']
        },
        {
            text         : 'Calendar',
            href         : '/jy/calendar',
            menuId       : '0402',
            menuUrl      : '/jy/calendar',
            menuName     : 'Calendar',
            menuLv       : 2,
            menuParentId : '04',
            tags         : ['4']
        },
        {
            text         : 'Todo List',
            href         : '/jy/todo',
            menuId       : '0403',
            menuUrl      : '/jy/todo',
            menuName     : 'Todo List',
            menuLv       : 2,
            menuParentId : '04',
            tags         : ['4']
        }
    ]},
    {
        text         : '연희',
        href         : '/yh/calendar',
        menuId       : '05',
        menuUrl      : '/yh/calendar',
        menuName     : '연희',
        menuLv       : 1,
        menuParentId : '00',
        tags         : ['4'],
        nodes        : [
        {
            text         : 'calendar',
            href         : '/yh/calendar',
            menuId       : '0501',
            menuUrl      : '/yh/calendar',
            menuName     : 'calendar',
            menuLv       : 2,
            menuParentId : '05',
            tags         : ['4']
        }
    ]},
    {
        text         : '정아',
        href         : '/ja/text',
        menuId       : '06',
        menuUrl      : '/ja/text',
        menuName     : '정아',
        menuLv       : 1,
        menuParentId : '00',
        tags         : ['4'],
        nodes        : [
        {
            text         : 'Text',
            href         : '/ja/text',
            menuId       : '0601',
            menuUrl      : '/ja/text',
            menuName     : 'Text',
            menuLv       : 2,
            menuParentId : '06',
            tags         : ['4']
        },
        {
            text         : 'Calendar',
            href         : '/ja/calendar',
            menuId       : '0602',
            menuUrl      : '/ja/calendar',
            menuName     : 'Calendar',
            menuLv       : 2,
            menuParentId : '06',
            tags         : ['4']
        }
    ]},
    {
        text         : 'Template',
        href         : '/template/themePage',
        menuId       : '07',
        menuUrl      : '/template/themePage',
        menuName     : 'Template',
        menuLv       : 1,
        menuParentId : '00',
        tags         : ['4'],
        nodes        : [
        {
            text         : 'Theme',
            href         : '/template/themePage',
            menuId       : '0701',
            menuUrl      : '/template/themePage',
            menuName     : 'Theme',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Text',
            href         : '/template/text',
            menuId       : '0702',
            menuUrl      : '/template/text',
            menuName     : 'Text',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Timeline',
            href         : '/template/timeline',
            menuId       : '0703',
            menuUrl      : '/template/timeline',
            menuName     : 'Timeline',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar1',
            href         : '/template/calendar1',
            menuId       : '0704',
            menuUrl      : '/template/calendar1',
            menuName     : 'Calendar1',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar2',
            href         : '/template/calendar2',
            menuId       : '0705',
            menuUrl      : '/template/calendar2',
            menuName     : 'Calendar2',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar3',
            href         : '/template/calendar3',
            menuId       : '0706',
            menuUrl      : '/template/calendar3',
            menuName     : 'Calendar3',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar4',
            href         : '/template/calendar4',
            menuId       : '0707',
            menuUrl      : '/template/calendar4',
            menuName     : 'Calendar4',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar5',
            href         : '/template/calendar5',
            menuId       : '0708',
            menuUrl      : '/template/calendar5',
            menuName     : 'Calendar5',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar6',
            href         : '/template/calendar6',
            menuId       : '0709',
            menuUrl      : '/template/calendar6',
            menuName     : 'Calendar6',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar7',
            href         : '/template/calendar7',
            menuId       : '0710',
            menuUrl      : '/template/calendar7',
            menuName     : 'Calendar7',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar8',
            href         : '/template/calendar8',
            menuId       : '0711',
            menuUrl      : '/template/calendar8',
            menuName     : 'Calendar8',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar9',
            href         : '/template/calendar9',
            menuId       : '0712',
            menuUrl      : '/template/calendar9',
            menuName     : 'Calendar9',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar10',
            href         : '/template/calendar10',
            menuId       : '0713',
            menuUrl      : '/template/calendar10',
            menuName     : 'Calendar10',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar11',
            href         : '/template/calendar11',
            menuId       : '0714',
            menuUrl      : '/template/calendar11',
            menuName     : 'Calendar11',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar12',
            href         : '/template/calendar12',
            menuId       : '0715',
            menuUrl      : '/template/calendar12',
            menuName     : 'Calendar12',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        },
        {
            text         : 'Calendar13',
            href         : '/template/calendar13',
            menuId       : '0716',
            menuUrl      : '/template/calendar13',
            menuName     : 'Calendar13',
            menuLv       : 2,
            menuParentId : '07',
            tags         : ['4']
        }
    ]}
]}];
let defaultData = [
    {
      text         : 'Parent 1',
      href         : '#parent1',
      tags         : ['4'],
      menuId       : 0,
      menuUrl      : '/',
      menuName     : 'Parent 1',
      menuLv       : 0,
      menuParentId : null,
      nodes: [
        {
          text: 'Child 1',
          href: '#child1',
          tags: ['2'],
          menuId       : 0,
          menuUrl      : '/',
          menuName     : 'Root',
          menuLv       : 0,
          menuParentId : 0,
          nodes: [
            {
              text: 'Grandchild 1',
              href: '#grandchild1',
              tags: ['0'],
              menuId       : 0,
              menuUrl      : '/',
              menuName     : 'Root',
              menuLv       : 0,
              menuParentId : 0,
            },
            {
              text: 'Grandchild 2',
              href: '#grandchild2',
              tags: ['0'],
              menuId       : 0,
              menuUrl      : '/',
              menuName     : 'Root',
              menuLv       : 0,
              menuParentId : 0,
            }
          ]
        },
        {
          text: 'Child 2',
          href: '#child2',
          tags: ['0'],
          menuId       : 0,
          menuUrl      : '/',
          menuName     : 'Root',
          menuLv       : 0,
          menuParentId : 0,
        }
      ]
    },
    {
      text: 'Parent 2',
      href: '#parent2',
      tags: ['0'],
      menuId       : 0,
      menuUrl      : '/',
      menuName     : 'Root',
      menuLv       : 0,
      menuParentId : 0,
    },
    {
      text: 'Parent 3',
      href: '#parent3',
       tags: ['0'],
       menuId       : 0,
       menuUrl      : '/',
       menuName     : 'Root',
       menuLv       : 0,
       menuParentId : 0,
    },
    {
      text: 'Parent 4',
      href: '#parent4',
      tags: ['0'],
      menuId       : 0,
      menuUrl      : '/',
      menuName     : 'Root',
      menuLv       : 0,
      menuParentId : 0,
    },
    {
      text: 'Parent 5',
      href: '#parent5'  ,
      tags: ['0'],
      menuId       : 0,
      menuUrl      : '/',
      menuName     : 'Root',
      menuLv       : 0,
      menuParentId : 0,
    }
  ];
  
//data 를 읽어서 menuId를 key로 하는 Map형식의 menuObj 에 값을 넣어준다.
let dataToObj = function(array)
{
    for(let data of array)
    {
        let tempObj =  Object.assign({}, data);
        if(tempObj.nodes !== undefined)
        {
            delete tempObj.nodes;
        }
        menuObj.set(tempObj.menuId,tempObj);

        if(data.nodes !== undefined)
        {
            dataToObj(data['nodes']);
        }
    }
}
let addToObj = function(array, targetObj)
{
	let targetMenuId = targetObj.menuParentId;
    let isFound = false;

    for(let data of array)
    {
        let tempObj =  Object.assign({}, data);

        if(data.menuId === targetMenuId)
        {
            console.log('찾았다!')
            isFound = true;
        }
        if(data.nodes !== undefined)
        {
            addToObj(data['nodes'],targetObj);
        }
    }
    if(isFound)
    {
        array.push(targetObj);
        return ;
    }
}
let makeMenuId = function(dataObj)
{
    let seq          = 0;
    let targetLv     = dataObj.menuLv;
    let targetParent = dataObj.menuParentId;
    let makedMenuId  = seq.toString().padStart(2, '0');  
    let trgLen       = targetLv * 2;
    
    if(dataObj.menuParentId === '00')
    {
        seq          = 0;                              
        makedMenuId  = seq.toString().padStart(2, '0');

        //seq 0번 노드가 없을때
        if(!menuObj.has(makedMenuId))
        {
            return makedMenuId;
        }
        //seq 0번 노드가 있을때
        for(let key of menuObj.keys())
        {
            if(key.length === trgLen)
            {
                let keySeq = parseInt(key);
                
                //비어있는 순번이 있으면 해당 순번을 menuId로 한다.
                if(keySeq !== seq)
                {
                    return seq.toString().padStart(2, '0');
                }
                seq ++ ;
            }
        }
        return seq.toString().padStart(2, '0');
    }
    else
    {
        seq          = 1;
        makedMenuId  = targetParent + seq.toString().padStart(2, '0');  
        
        //seq 1번 노드가 없을때
        if(!menuObj.has(makedMenuId))
        {
            return makedMenuId;
        }
        //seq 1번 노드가 있을때
        for(let key of menuObj.keys())
        {
            if(targetParent === key.substring(0, (trgLen - 2)) && key.length === trgLen)
            {
                let keySeq = parseInt(key.replace(targetParent, ''));
                
                //비어있는 순번이 있으면 해당 순번을 menuId로 한다.
                if(keySeq !== seq)
                {
                    return targetParent + seq.toString().padStart(2, '0');
                }
                seq ++ ;
            }
        }
        return targetParent + seq.toString().padStart(2, '0');
    }
}

function setModifyForm(dataObj) 
{
    if(dataObj === undefined)
    {
        $('input[name=traversing]').prop('checked', false);
        $('#menuForm')[0].reset();
        $('#addMenu').hide();
    }
    else
    {
        $('#menuId').val(      dataObj.menuId      );   
        $('#menuParentId').val(dataObj.menuParentId);  
        $('#menuUrl').val(     dataObj.menuUrl     );  
        $('#menuName').val(    dataObj.menuName    );  
        $('#menuLv').val(      dataObj.menuLv      );
    }
}
function onNodeSelect(event, node) 
{
    if(event.handleObj.type === 'nodeUnselected')
    {
        $('#btnGroup').hide();
        isNodeSelected = false;
        selectedObj    = null;
        setModifyForm();
    }
    if(event.handleObj.type === 'nodeSelected')
    {
        $('#btnGroup').show();
        selectedObj    = Object.assign({}, node);
        delete selectedObj.nodeId;
        delete selectedObj.parentId;
        delete selectedObj.selectable;
        delete selectedObj.state;
        isNodeSelected = true;
        setModifyForm(selectedObj);
    }
}
$(document).ready(()=>
{
    
    var treeViewOption = {
            data: dummyData,
            onNodeSelected  : onNodeSelect,
            onNodeUnselected: onNodeSelect
    };
    
    
    var initSelectableTree = function() {
        return $('#treeview2').treeview(treeViewOption);
      };

      menuObj.clear();//dataToObj 하기전에 미리 menuObj를 초기화한다.
      dataToObj(dummyData);
      var $selectableTree = initSelectableTree();
      
      $('#chngFirst').on('click', (event)=>
      {
          let isChecked = $(event.target).prop('checked');
          if(isChecked)
          {
              treeViewOption['data'] = firstTreeData;
              menuObj.clear();//dataToObj 하기전에 미리 menuObj를 초기화한다.
              dataToObj(firstTreeData);
              $selectableTree.treeview(treeViewOption);
          }
          else
          {
              treeViewOption['data'] = dummyData;
              menuObj.clear();//dataToObj 하기전에 미리 menuObj를 초기화한다.
              dataToObj(dummyData);
              $selectableTree.treeview(treeViewOption);
          }
      });
      
      //형제/자식 메뉴추가 버튼 클릭시
      $('input[name=traversing]').on('click', (event)=>
      {
          //메뉴를 선택하지 않았을때
          if(!isNodeSelected)
          {
              alert("메뉴를 선택해주세요");
              setModifyForm();
              $('#addMenu').hide();
              return;
          }
          $('#addMenu').show();
          $('#btnGroup').hide();
          
          //자식 메뉴 추가시
          if($(event.target).attr('id') === 'addChild')
          {
              $('#addMenu').show();
              let tempObj = Object.assign({},selectedObj);
              
              tempObj['menuLv']       = tempObj['menuLv']+ 1;
              tempObj['menuParentId'] = tempObj['menuId'];
              tempObj['menuId']       = makeMenuId(tempObj);
              
              setModifyForm(tempObj);
          }
          //형제 메뉴 추가시
          if($(event.target).attr('id') === 'addSiblings')
          {
              if(parseInt(selectedObj.menuId) === 0)
              {
                  alert('Root 에는 형제 메뉴를 추가할 수 없습니다.');
                  $('input[name=traversing]').prop('checked', false);
                  setModifyForm(selectedObj);
                  $('#addMenu').hide();
                  return;
              }
              $('#addMenu').show();
              let tempObj = Object.assign({},selectedObj);

              tempObj['menuId']       = makeMenuId(tempObj);
              
              setModifyForm(tempObj);
          }
      });
      $('#btnCollapse').on('click', ()=>
      {
          $selectableTree.treeview('collapseAll', { silent: true });
      });
      $('#addMenu').on('click', ()=>
      {
          console.log(dummyData);
          
          let addDataObject = {
              text         : $('#menuName').val(),
              href         : $('#menuUrl').val(),
              menuId       : $('#menuId').val(),      
              menuParentId : $('#menuParentId').val(),
              menuLv       : $('#menuLv').val(),      
              menuUrl      : $('#menuUrl').val(),     
              menuName     : $('#menuName').val(),    
              tags         : ['0']
          }
              
          console.log(addDataObject.menuParentId);
          addToObj(dummyData, addDataObject);
          console.log(dummyData);
      });

});


</script>
    
<div class="modal fade" id="myModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">new Memo</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true"></span>
        </button>
      </div>
      <div class="modal-body">
        <form>

        <div class="mb-3">
          <input type="text" class="form-control" id="content" name="content" rows="3" placeholder="제목"/>
        </div>
        <div class="mb-3">
          <textarea class="form-control" id="content" name="content" rows="3" placeholder="내용을 입력하세요"></textarea>
        </div>
        
        </form>
      </div>
      <div class="modal-footer" style="justify-content: space-between;">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" value="Y" id="todo-check">
          <label class="form-check-label" for="todo-check">
            Todo Memo
          </label>
        </div>
        <div>
            <button type="button" class="btn btn-primary">저장</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="container">
      <div class="row">
            <div class="d-flex gap-2 justify-content-center pb-5">
                <button class="bi btn btn-primary rounded-circle p-3 lh-1" type="button" style="font-size:3em;"  data-bs-toggle="modal" data-bs-target="#myModal" ><i class="bi bi-plus-lg"></i></button>
<%--                 <a href="${path}/wc/createCard" style="font-size:2em;"><i class="bi bi-plus-circle-fill"></i></a> --%>
                <button class="bi btn btn-primary rounded-circle p-3 lh-1" type="button" style="font-size:3em; background-color:#888; border-color:#888;"><i class="bi bi-mic"></i></button>
            </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
            <div class="card mb-3">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1">Menu setting</h5>
                  </div>
              </div>
              <div class="card-body" style="display:flex;flex-direction:row;">
              
                  <div class="col-lg-6">
                      <div class="btn-group mt-2 mb-2" role="group" aria-label="Basic radio toggle button group">
                          <input type="radio" class="btn-check" name="traversing" id="addChild" autocomplete="off">
                          <label class="btn btn-outline-primary" for="addChild">자식 메뉴 추가</label>
                          <input type="radio" class="btn-check" name="traversing" id="addSiblings" autocomplete="off">
                          <label class="btn btn-outline-primary" for="addSiblings">형제 메뉴 추가</label>
                      </div>
                      <input type="button" class="btn btn-primary" id="btnCollapse" value="Collapse All">
                      <div id="treeview2" class=""></div>
                      <div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
                        <input type="checkbox" class="btn-check" id="chngFirst" autocomplete="off">
                        <label class="btn btn-primary mt-2" for="chngFirst">Change First Data</label>
                      </div>
                  </div>
              
                  <div class="col-lg-6 p-3">
                      <form id="menuForm">
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuId">MenuId</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="Menu Id" id="menuId" readonly>
                          </span>
                      </div>
                      
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuParentId">MenuParentId</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="Menu Parent Id" id="menuParentId" readonly>
                          </span>
                      </div>
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuLv">Menu Level</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="menuLv" id="menuLv" readonly>
                          </span>
                      </div>
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuUrl">Menu Url</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="Menu Url" id="menuUrl">
                          </span>
                      </div>
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuName">Menu Name</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="menuName" id="menuName">
                          </span>
                      </div>
                      <input type="button" class="btn btn-primary mt-3"  id="addMenu" value="추가" style="display:none;">
                      <div class="btn-group mt-3" style="display:none;" id="btnGroup">
                      <input type="button" class="btn btn-primary"  id="modMenu" value="수정">
                      <input type="button" class="btn btn-danger"   id="delMenu" value="삭제">
                      </div>
                       </form>
                  </div>
              
              
              </div>
              <div style="text-align:center;"><img src="${path}/img/Pensieve.webp" width="100%"/></div>
              <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
              </div>
              <ul class="list-group list-group-flush">
                <li class="list-group-item">Cras justo odio</li>
                <li class="list-group-item">Dapibus ac facilisis in</li>
                <li class="list-group-item">Vestibulum at eros</li>
              </ul>
              <div class="card-body">
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                2 days ago
              </div>
          </div>
        </div>
          
        <div class="col-lg-4 mb-3">
            <div class="card">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1"></h5>
                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bi bi-three-dots-vertical"></i>
                      </a>
                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                      </div>
                  </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title1</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                3 days ago
              </div>
            </div>
        </div>
        <div class="col-lg-4 mb-3">
            <div class="card">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1"></h5>
                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bi bi-three-dots-vertical"></i>
                      </a>
                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                      </div>
                  </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title2</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                4 days ago
              </div>
            </div>
        </div>
        <div class="col-lg-4 mb-3">
            <div class="card">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1"></h5>
                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bi bi-three-dots-vertical"></i>
                      </a>
                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                      </div>
                  </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title3</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                5 days ago
              </div>
            </div>
        </div>
        <div class="col-lg-4 mb-3">
            <div class="card">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1"></h5>
                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bi bi-three-dots-vertical"></i>
                      </a>
                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                      </div>
                  </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title4</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
              <div style="text-align:center;"><img src="${path}/img/Pensieve.webp" width="100%"/></div>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                6 days ago
              </div>
            </div>
        </div>
        <div class="col-lg-4 mb-3">
            <div class="card border-secondary">
              <div class="card-header border-secondary mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1">Todo Memo</h5>
                      
                      <div class="d-flex justify-flex-end">
<!--                       <div> -->
                          <div>
                              <input class="form-check-input" type="checkbox" value="Y" id="todo-check">
                          </div>
                          <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              <i class="bi bi-three-dots-vertical"></i>
                          </a>
                          <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                            <a class="dropdown-item" href="#">수정</a>
                            <a class="dropdown-item" href="#">삭제</a>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title5</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted align-right">
                7 days ago
              </div>
            </div>
        </div>
      </div>
      <div class="row m-3">
      <hr>
            <div class="d-flex gap-2 justify-content-center pb-5">
                <div class="spinner-border text-warning" style="width:3em;height:3em;border-width:0.6em;"></div>
            </div>
      </div>
      <div class="row m-3">
      <hr>
            <div class="d-flex gap-2 justify-content-center pb-5" style="font-size:3em;">
                <i class="bi bi-x-circle-fill"></i>
            </div>
      </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    