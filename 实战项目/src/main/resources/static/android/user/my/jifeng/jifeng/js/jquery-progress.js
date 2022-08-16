;(function() {
     $.fn.startProgress=function(data){
     /*初始化*/
     var ele=this;
     ele.append('<div class="yixif">'
			    +'<span class="yixiaof">0</span><br/>'
			    +'<img src="images/position.png" class="dingwei"></div>'		
				+'<div class="xianpa">'
				+'<div class="hengxian">'
				+'<div class="hongxian"></div>'
				+'</div></div>'
				+'<div class="flexbox djwai"></div>');
	 if(data&&data.nodes&&data.hasPay){
	 	/*currentNode当前节点数;overMoney超所在的区间下限额度;temp临时变量;redLine进度条长度占比;*/
	 	var hStr='',currentNode=0,overMoney=0,temp=0,redLine=0;
	 	/*构造节点数据*/
	 	for(var node in data.nodes){
	 		if(data.nodes[node].money<=data.hasPay){
	 			currentNode+=1;
	 			temp=data.nodes[node].money;
	 		}
	 		overMoney=data.hasPay-temp;
	 		hStr='<div class="yifen dengji '+(node>0?'gray':'')+'">'
				+'<img src="images/juyuandian.png">'
				+'<div class="jucol">'+data.nodes[node].name+'</div>'
				/*+'<div>满'+data.nodes[node].money+'元</div>'
			    +'</div>';*/
	 		ele.find(".djwai").append(hStr);
	 	}
	 	/*计算进度条位置*/
	 	if(currentNode&&currentNode>0&&currentNode<=data.nodes.length){
	 		if(currentNode==data.nodes.length){
               redLine=100;
	 		}else{
	 	    redLine=(currentNode-1)/(data.nodes.length-1);
	 	    redLine+=(overMoney/(data.nodes[currentNode].money
	 	    	-data.nodes[currentNode-1].money))*(1/(data.nodes.length-1));
	 	    redLine=redLine*100;
	 	    redLine=redLine>100?100:redLine;
	 	    redLine=redLine<0?0:redLine;
	 	    }
	 	}
        /*进度条滑动特效*/
        var t=1000,i=0,speedup=14,startspeed=1,currentMoney=0;
	      setTimeout(function(){
	       var timer=setInterval(function(){
                  if(i<=500){
                  	i+=startspeed+((i/500)*speedup);
                  }else if(i>500){
                  	i+=startspeed+speedup-+(((i-500)/500)*speedup);
                  }
                  currentMoney=parseInt(i*data.hasPay/t);
	       	      if(i>=t){i=t;currentMoney=data.hasPay;}
	              setPosition((redLine*(i/t)),currentMoney);
	              if(i>=t){clearInterval(timer);}
	        },16.67);
	        },1000);
		 }
		 /*设置进度条当前位置*/
		 function setPosition(p,money){
		 	for(var n in data.nodes){
              if(p/100>=n/(data.nodes.length-1)){
                 $(".yifen").eq(n).removeClass('gray');
              }
		 	}
			ele.find(".dingwei").css("left",p+'%');
	        ele.find(".hongxian").css("width",p+'%');
	        ele.find(".yixiaof").css("left",p+'%'); 
	        ele.find(".yixiaof").text(''+money+'');
		 }
    }})();