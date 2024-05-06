$(document).ready(function() {
	//
	if ($('#error').val() === 'true') {
		$.messager.alert('Warning', $('#errorMessage').val(), 'warning');
	}
	//
	$(".easyui-textbox").each(function() {
        var options = {
            label: $(this).attr("data-label"),
            required: $(this).attr("data-required") === "true",
            prompt: $(this).attr("data-prompt"),
            iconCls: $(this).attr("data-icon-cls")
        };
        $(this).textbox(options);
    });
	//
	var currentLang = $('#currentLang').val();
	initI18nSelector(currentLang);
});

/**
 * 
 * @returns
 */
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

/**
 * 
 * @returns
 */
function resetForm(){
    $('#ff').form('clear');
}

/**
 * 
 * @returns
 */
function backForm() {
    window.history.back();
}

/**
 * 
 * @param currentLang
 * @returns
 */
function initI18nSelector(currentLang) {
	// 初始化国际化语言
    var languages = {
	    'en': { url: '/i18n/change?lang=en', text: I18n.langTexts.en },
	    'zh_CN': { url: '/i18n/change?lang=zh_CN', text: I18n.langTexts.zh_CN }
    };
    // 将对象转换为数组适用于 combobox 的数据源格式
    var languageArray = Object.keys(languages).map(function(key) {
        return { id: languages[key].url, text: languages[key].text };
    });
    // 初始化国际化选择器
    $('#i18nSelector').combobox({
        data: languageArray,
        valueField: 'id',
        textField: 'text',
        editable: false,
        panelHeight: 'auto',
        onLoadSuccess: function() {
            // 使用当前语言的URL设置combobox的值
            $(this).combobox('setValue', languages[currentLang].url);
        },
        onChange: function(newValue, oldValue) {
            if (oldValue && newValue !== oldValue) {
                window.location.href = newValue;
            }
        }
    });
}