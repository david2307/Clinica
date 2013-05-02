$(function (){
    $("#pacientes").click(function (e){
        e.preventDefault();
        $.ajax({
            url: '../pacientes/mostrarCrearPaciente',
            dataType: 'html',
            type: 'GET',
            success: function (result) {
                $("#content-layout").html(result);
            }
        });
        return false
    });
});