$(function () {
    $.get("header.html?"+new Date().getTime(),function (data) {
        $("#header").html(data);
    });
    $.get("footer.html?"+new Date().getTime(),function (data) {
        $("#footer").html(data);
    });
    $.ajax({
      url:"http://localhost:8082/getUsername",
      contentType:"application/json",
      type:"get",
      dataType:"json",
      success:function(result){
        if(result.result){
          $("#userProfilePanel").html(result.result);
          $("#noauth_info").hide();
          $("#member_info").show();
        }else{
          $("#noauth_info").show();
          $("#member_info").hide();
        }
      },
      error:function(){
        $("#noauth_info").show();
        $("#member_info").hide();
      }
    });
});
