(function () {

  var page = 0;
  var isLoading = false;

  function getList () {
    page++;
    isLoading = true;
    $.get('/orderAndroid/map', function (data) {

      var list = data.data

      initContentList(list);
      isLoading = false;
    });

    /*$.get('../json/orders.json', function (data) {

      var list = data.data.digestlist;

      initContentList(list);
      isLoading = false;
    });*/
  }

  function initContentList (list) {
    var str = ``;
    list.forEach(element => {
      str += `
      <div class="order-item">
        <div class="order-item-inner">
          <img class="item-img" src="https://s1.ax1x.com/2022/08/12/vYyUxJ.png"/>
          <div class="item-right">
            <div class="item-top">
              <p class="order-name one-line">${element.startAddress}→${element.destination}</p>
              <div class="arrow" onclick="getOrderDetail(${element.id})"></div>
              <div class="order-state">${element.status_Str}</div>
            </div>
          </div>
        </div>
        ${getComment(element)}
      </div>
    `
    });

    $('.order-list').append($(str));
  }

  function getComment (element) {
      return `
        <div class="evaluation clearfix">
            <button class="evaluation-btn" onclick="getLoc(${element.id})">查看司机当前位置</button>
        </div>
      `
  }


  function addEvent () {
    window.addEventListener('scroll', function () {
      var clientHeight = document.documentElement.clientHeight;
      var scrollHeight = document.body.scrollHeight;
      var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;

      var preDis = 30;

      if (scrollTop + clientHeight + preDis >= scrollHeight) {
        if (page < 1) {
          if (isLoading) {
            return ;
          }
          getList();
        } else {
          $('.loading').text('加载完成');
        }
      }
    })
  }

  function init () {
    getList();
    addEvent();
  }

  init();
})();