/// <reference path="JSintellisense/jquery-1.2.6-intellisense.js" />

//ȫ�ֱ�������
var CANVAS_WIDTH = 284; //�����Ŀ�
var CANVAS_HEIGHT = 266; //�����ĸ�
//var ICON_SIZE = 120;  //��ȡ��Ĵ�С��������
var ICON_WIDTH = 150;   //��ȡ��Ŀ�
var ICON_HEIGHT = 112;   //��ȡ��Ŀ�
var LEFT_EDGE = (CANVAS_WIDTH - ICON_WIDTH) / 2; //82
var TOP_EDGE = (CANVAS_HEIGHT - ICON_HEIGHT) / 2; //73
var CANVAS_LEFT_MARGIN = 4;


//��document. ready�¼������ϴ����һ��ת��ʱ�޷���ȡ��ͼƬ�Ĵ򿪣�Ӧ����û�б���������Ե��
function initImage() {
    var $iconElement = $("#ImageIcon");
    var $imagedrag = $("#ImageDrag");

    //��ȡ�ϴ�ͼƬ��ʵ�ʸ߶ȣ����
    var image = new Image();
    image.src = $iconElement.attr("src");
    var realWidth = image.width;
    var realHeight = image.height;  
    image = null;  
   
    //�������ű�����ʼ
    var minFactor = Math.max(ICON_WIDTH,ICON_HEIGHT) / Math.max(realWidth, realHeight);
    if (ICON_WIDTH > realWidth && ICON_HEIGHT > realHeight) {
        minFactor = 1;
    }
    var factor = minFactor > 0.25 ? 8.0 : 4.0 / Math.sqrt(minFactor);

    //ͼƬ���ű���
    var scaleFactor = 1;
    if (realWidth > CANVAS_WIDTH && realHeight > CANVAS_HEIGHT) {
        if (realWidth / CANVAS_WIDTH > realHeight / CANVAS_HEIGHT) {
            scaleFactor = CANVAS_HEIGHT / realHeight;
        }
        else {
            scaleFactor = CANVAS_WIDTH / realWidth;
        }
    }
    //�������ű�������

    //���û�������λ�ã����û�������ֵ�ı仯���ı���������һ�������Եı仯�������ݺ������� ������y=factor��X��--�˴�xΪ��ָ��
    //�˴�100 * (Math.log(scaleFactor * factor) / Math.log(factor)) Ϊ֪��y ���x ���㷨
    $(".child").css("left", 100 * (Math.log(scaleFactor * factor) / Math.log(factor)) + "px");

    //ͼƬʵ�ʳߴ磬�����Ƶ�����
    var currentWidth = Math.round(scaleFactor * realWidth);
    var currentHeight = Math.round(scaleFactor * realHeight);

    //ͼƬ���CANVAS�ĳ�ʼλ�ã�ͼƬ��Ի�������
    var originLeft = Math.round((CANVAS_WIDTH - currentWidth) / 2) ;
    var originTop = Math.round((CANVAS_HEIGHT - currentHeight) / 2);
    //�����ȡ���е�ͼƬ��λ��
    var dragleft = originLeft - LEFT_EDGE;
    var dragtop = originTop - TOP_EDGE;


    //����ͼƬ��ǰ�ߴ��λ��
    $iconElement.css({ width: currentWidth + "px", height: currentHeight + "px", left: originLeft + "px", top: originTop + "px" });
    $imagedrag.css({ width: currentWidth + "px", height: currentHeight + "px", left: dragleft + "px", top: dragtop + "px" });

    //��ʼ��Ĭ��ֵ
    $("#txt_width").val(currentWidth);
    $("#txt_height").val(currentHeight);
    $("#txt_top").val(0-dragtop);
    $("#txt_left").val(0-dragleft);
    $("#txt_Zoom").val(scaleFactor);

    var oldWidth = currentWidth;
    var oldHeight = currentHeight;

    //����ͼƬ����ק��������קһ��ͼƬʱ��ͬʱ�ƶ�����һ��ͼƬ
    $imagedrag.draggable(
    {
        cursor: 'move',
        drag: function(e, ui) {
            var self = $(this).data("draggable");
            var drop_img = $("#ImageIcon");
            var top = drop_img.css("top").replace(/px/, ""); //ȡ����ȡ�򵽶����ľ���
            var left = drop_img.css("left").replace(/px/, ""); //ȡ����ȡ����ߵľ���
            drop_img.css({ left: (parseInt(self.position.left) + LEFT_EDGE) + "px", top: (parseInt(self.position.top) + TOP_EDGE) + "px" }); //ͬʱ�ƶ��ڲ���ͼƬ
            $("#txt_left").val(0 - parseInt(self.position.left));
            $("#txt_top").val(0 - parseInt(self.position.top));
        }
    }
    );
    //����ͼƬ����ק��������קһ��ͼƬʱ��ͬʱ�ƶ�����һ��ͼƬ
    $iconElement.draggable(
    {
        cursor: 'move',
        drag: function(e, ui) {
            var self = $(this).data("draggable");
            var drop_img = $("#ImageDrag");
            var top = drop_img.css("top").replace(/px/, ""); //ȡ����ȡ�򵽶����ľ���
            var left = drop_img.css("left").replace(/px/, ""); //ȡ����ȡ����ߵľ���
            drop_img.css({ left: (parseInt(self.position.left) - LEFT_EDGE) + "px", top: (parseInt(self.position.top) - TOP_EDGE) + "px" }); //ͬʱ�ƶ��ڲ���ͼƬ
            $("#txt_left").val(0 - (parseInt(self.position.left) - LEFT_EDGE));
            $("#txt_top").val(0 - (parseInt(self.position.top) - TOP_EDGE));
        }

    }
    );

    //���ŵĴ��룬Ҫ�����Խ�ȡ��Ϊ���ģ�����Ҫ���¼���ͼƬ��left��top
    $(".child").draggable(
  {
      cursor: "move", containment: $("#bar"),
      drag: function(e, ui) {
      var left = parseInt($(this).css("left"));
          //ǰ�潲����y=factor��x�����˴���֪��x��y��ֵ����֪����������λ�ã���ȡ������
          scaleFactor = Math.pow(factor, (left / 100 - 1));
          if (scaleFactor < minFactor) {
              scaleFactor = minFactor;
          }
          if (scaleFactor > factor) {
              scaleFactor = factor;
          }
          //���´���ͬ��ʼ�����̣���Ϊ�õ��ֲ���������û��
          var iconElement = $("#ImageIcon");
          var imagedrag = $("#ImageDrag");

          var image = new Image();
          image.src = iconElement.attr("src");
          var realWidth = image.width;
          var realHeight = image.height;
          image = null;

          //ͼƬʵ�ʳߴ�
          var currentWidth = Math.round(scaleFactor * realWidth);
          var currentHeight = Math.round(scaleFactor * realHeight);

          //ͼƬ���CANVAS�ĳ�ʼλ��
          var originLeft = parseInt(iconElement.css("left"));
          var originTop = parseInt(iconElement.css("top"));

          originLeft -= Math.round((currentWidth - oldWidth) / 2);
          originTop -= Math.round((currentHeight - oldHeight) / 2);
          dragleft = originLeft - LEFT_EDGE;
          dragtop = originTop - TOP_EDGE;

          //ͼƬ��ǰ�ߴ��λ��
          iconElement.css({ width: currentWidth + "px", height: currentHeight + "px", left: originLeft + "px", top: originTop + "px" });
          imagedrag.css({ width: currentWidth + "px", height: currentHeight + "px", left: dragleft + "px", top: dragtop + "px" });

          $("#txt_Zoom").val(scaleFactor);
          $("#txt_left").val(0 - dragleft);
          $("#txt_top").val(0 - dragtop);
          $("#txt_width").val(currentWidth);
          $("#txt_height").val(currentHeight);
          oldWidth = currentWidth;
          oldHeight = currentHeight;

      }
  });
    var SilderSetValue = function(i) {
        var left = parseInt($(".child").css("left"));
        left += i;

        if (left < 0) {
            left = 0;
        }
        if (left > 200) {
            left = 200;
        }

        scaleFactor = Math.pow(factor, (left / 100 - 1));
        if (scaleFactor < minFactor) {
            scaleFactor = minFactor;
        }
        if (scaleFactor > factor) {
            scaleFactor = factor;
        }
        var iconElement = $("#ImageIcon");
        var imagedrag = $("#ImageDrag");

        var image = new Image();
        image.src = iconElement.attr("src");
        var realWidth = image.width;
        var realHeight = image.height;
        image = null;

        //ͼƬʵ�ʳߴ�
        var currentWidth = Math.round(scaleFactor * realWidth);
        var currentHeight = Math.round(scaleFactor * realHeight);

        //ͼƬ���CANVAS�ĳ�ʼλ��
        var originLeft = parseInt(iconElement.css("left"));
        var originTop = parseInt(iconElement.css("top"));

        originLeft -= Math.round((currentWidth - oldWidth) / 2);
        originTop -= Math.round((currentHeight - oldHeight) / 2);
        dragleft = originLeft - LEFT_EDGE;
        dragtop = originTop - TOP_EDGE;

        //ͼƬ��ǰ�ߴ��λ��
        $(".child").css("left", left + "px");
        iconElement.css({ width: currentWidth + "px", height: currentHeight + "px", left: originLeft + "px", top: originTop + "px" });
        imagedrag.css({ width: currentWidth + "px", height: currentHeight + "px", left: dragleft + "px", top: dragtop + "px" });

        $("#txt_Zoom").val(scaleFactor);
        $("#txt_left").val(0 - dragleft);
        $("#txt_top").val(0 - dragtop);
        $("#txt_width").val(currentWidth);
        $("#txt_height").val(currentHeight);

        oldWidth = currentWidth;
        oldHeight = currentHeight;
    }
    //����Ӽ���
    $("#moresmall").click(function() {
        SilderSetValue(-20);
    });
    $("#morebig").click(function() {
        SilderSetValue(20);
    });

}