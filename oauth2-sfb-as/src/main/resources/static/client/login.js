$(document).ready(function() {
	if ($('#error').val() === 'true') {
		$.messager.alert('Warning', $('#errorMessage').val(), 'warning');
	}
});

function submitForm(){
    $('#ff').form('submit', {
        onSubmit: function() {
            var isValid = $(this).form('enableValidation').form('validate');
            if (isValid) {
                // 通过原生方式提交表单，避免Ajax导致的X-Frame-Options问题
                document.getElementById('ff').submit();
            }
            return false; // 阻止表单的自动提交，只有在验证成功时才进行提交
        }
    });
}

function resetForm(){
    $('#ff').form('clear');
}

function backForm() {
    window.history.back();
}
