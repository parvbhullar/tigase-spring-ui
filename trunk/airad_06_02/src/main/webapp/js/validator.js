
/**
 * @copyright MitianTech
 * @version 1.0.a
 * @since 2011-03-12
 */
if (!!window.jQuery) {
    (function($){
        var debugMode = false && window.console;
        var ajaxAsync = true;
        var miceBaseURI = '/';
        var miceValidator = {
            /**
             * @namespace control
             */
            control: {
                _formConfigs: null,
                init: function(){
                    var allForms = $('form[class*="ppt."]');
                    for (var i = 0, j = allForms.length; i < j; i++) {
                        var formElement = allForms.get(i);
                        var classNames = $(formElement).attr('class').split(' ');
                        for (var k = 0, l = classNames.length; k < l; k++) {
                            if (classNames[k].indexOf('ppt.') >= 0) {
                                var ruleURI = miceBaseURI + 'ppt/$$$.json'.replace('$$$', classNames[k].substr(4));
                                if (debugMode) {
                                    console.log('Get Rule: ' + ruleURI);
                                };
                                $.ajax({
                                    url: ruleURI,
                                    dataType: "text",
                                    async: false,
                                    success: function(json){
                                        var jsonObj = getdata(json);
                                        function getdata(data){
                                            return (new Function("return " + data))();
                                        }
                                        window.MICE.control.injectRules(jsonObj, formElement);
                                    }
                                });
                                //                                $.getJSON(ruleURI, {}, function(json) {
                                //                                    window.MICE.control.injectRules(json, formElement);
                                //                                });
                                break;
                            }
                        }
                    }
                },
                injectRules: function(formRule, formElement){
                    var formSetting = formRule.globleSetting;
                    var formSelector = 'form[name="' + ($(formElement).attr('name') || formSetting.formName) + '"]';
                    formSetting.validatorForm = formSelector;
                    var globalConfig = window.MICE.control.initConfig({
                        validatorForm: formSelector
                    });
                    if (debugMode) {
                        console.info('Start init: formSelector<' + formSetting.formName + '>.');
                    }
                    var elementRules = formRule.fields;
                    $.each(elementRules, function(i, elementRule){
                        var jQElement = $('#' + elementRule.name);
                        if (0 < jQElement.length) {
                            if (debugMode) {
                                console.log('-- Element :input[id="' + elementRule.name + '"]');
                            }
                        }
                        else {
                            jQElement = 0 < $(formSelector).length ? $(formSelector).find(':input[name="' + elementRule.name + '"]') : $(':input[name="' + elementRule.name + '"]');
                            if (0 < jQElement.length) {
                                if (debugMode) {
                                    console.log('-- Element :input[name="' + elementRule.name + '"]');
                                }
                            }
                            else {
                                if (debugMode) {
                                    console.info('Unknown Element :input[name="' + elementRule.name + '"]!');
                                }
                                return;
                            }
                        }
                        var elementSetting = $.extend({}, formSetting, elementRule);
                        jQElement.each(function(){
                            var elThis = this;
                            window.MICE.control.addOneRule(elThis, $.extend({
                                validateType: 'InitValidator'
                            }, elementSetting));
                            if (!!elementRule.range) {
                                window.MICE.control.addOneRule(elThis, $.extend({
                                    validateType: 'RangeValidator'
                                }, elementSetting, elementRule.range));
                            }
                            if (!!elementRule.pattern) {
                                $.each(elementRule.pattern, function(j, patternRule){
                                    var sType = elThis.type;
                                    if ('checkbox' == sType || 'radio' == sType && 'required' == patternRule.regExp) {
                                        window.MICE.control.addOneRule(elThis, $.extend({
                                            validateType: 'RangeValidator'
                                        }, elementSetting, $.extend(patternRule, {
                                            dataType: 'size',
                                            min: 1,
                                            max: Number.POSITIVE_INFINITY
                                        })));
                                    }
                                    window.MICE.control.addOneRule(elThis, $.extend({
                                        validateType: 'PatternValidator'
                                    }, elementSetting, patternRule));
                                });
                            }
                            if (!!elementRule.callback) {
                                $.each(elementRule.callback, function(j, callbackRule){
                                    window.MICE.control.addOneRule(elThis, $.extend({
                                        validateType: 'CallbackValidator'
                                    }, elementSetting, callbackRule));
                                });
                            }
                            if (!!elementRule.compare) {
                                $.each(elementRule.compare, function(j, compareRule){
                                    window.MICE.control.addOneRule(elThis, $.extend({
                                        validateType: 'CompareValidator'
                                    }, elementSetting, compareRule));
                                });
                            }
                            if (!!elementRule.ajax) {
                                window.MICE.control.addOneRule(elThis, $.extend({
                                    validateType: 'AjaxValidator'
                                }, elementSetting, elementRule.ajax));
                            }
                            window.MICE.control.addValidator(elThis, elementSetting);
                        });
                    });
                    if (debugMode) {
                        console.log('-- Element :' + formSelector + '');
                    };
                    $(formSelector).submit(window.MICE.control.formValidator);
                    if (debugMode) {
                        console.info('Finish init: formSelector<' + formSetting.formName + '>.');
                    }
                },
                getConfig: function(validatorForm){
                    var _formConfigs = window.MICE.control._formConfigs;
                    if (null != _formConfigs) {
                        for (var i = 0, j = _formConfigs.length; i < j; i++) {
                            if (validatorForm == _formConfigs[i].validatorForm) {
                                return _formConfigs[i];
                            }
                        }
                        return _formConfigs[0];
                    }
                    return null;
                },
                
                initConfig: function(newConfig){
                    var configSetting = $.extend({}, window.MICE.rule.globalConfig, newConfig ||
                    {});
                    if (!!configSetting.tidyMode) {
                        configSetting.setFocus = false;
                    };
                    if (null == window.MICE.control._formConfigs) {
                        window.MICE.control._formConfigs = new Array();
                    }
                    window.MICE.control._formConfigs.push(configSetting);
                    return configSetting;
                },
                addOneRule: function(el, newSetting){
                    if (!newSetting || !newSetting.validateType) {
                        return false;
                    }
                    var setting = {};
                    switch (newSetting.validateType) {
                        case 'InitValidator':
                            setting = $.extend({}, window.MICE.rule.initSetting, newSetting);
                            if (!setting.validatorForm) {
                                setting.validatorForm = 'form0';
                            }
                            if (!!setting.tidyMode) {
                                setting.tipCSS = {
                                    'left': '2px',
                                    'width': '22px',
                                    'height': '22px',
                                    'display': 'none'
                                }
                            }
                            if (!setting.tipSelector) {
                                setting.tipSelector = '#' + el.name.replace('.', '_') + '_Tip';
                            }
                            break;
                        case 'RangeValidator':
                            setting = $.extend({}, window.MICE.rule.rangeSetting, newSetting);
                            break;
                        case 'PatternValidator':
                            setting = $.extend({}, window.MICE.rule.patternSetting, newSetting);
                            break;
                        case 'CallbackValidator':
                            setting = $.extend({}, window.MICE.rule.callbackSetting, newSetting);
                            break;
                        case 'CompareValidator':
                            setting = $.extend({}, window.MICE.rule.compareSetting, newSetting);
                            break;
                        case 'AjaxValidator':
                            setting = $.extend({}, window.MICE.rule.ajaxSetting, newSetting);
                            break;
                    }
                    return window.MICE.control.appendSetting(el, setting);
                },
                appendSetting: function(el, setting){
                    if (!window.MICE.control.confirmValidate(el, setting)) {
                        return -1;
                    }
                    if ('InitValidator' == setting.validateType || !el.settings) {
                        el.settings = new Array();
                    }
                    var len = el.settings.push(setting);
                    el.settings[len - 1].index = len - 1;
                    return len - 1;
                },
                confirmValidate: function(el, setting){
                    var sTag = el.tagName;
                    var sType = el.type;
                    switch (setting.validateType) {
                        case 'InitValidator':
                            return true;
                        case 'RangeValidator':
                            return true;
                        case 'PatternValidator':
                            if ('checkbox' == sType || 'radio' == sType || 'select-multiple' == sType) {
                                return false;
                            }
                            else {
                                return true;
                            }
                        case 'CallbackValidator':
                            return true;
                        case 'CompareValidator':
                            if ('INPUT' == sTag || 'TEXTAREA' == sTag) {
                                if ('checkbox' == sType || 'radio' == sType) {
                                    return false;
                                }
                                else {
                                    return true;
                                }
                            }
                            return false;
                        case 'AjaxValidator':
                            if ('text' == sType || 'textarea' == sType || 'file' == sType || 'password' == sType || 'select-one' == sType) {
                                return true;
                            }
                            else {
                                return false;
                            }
                        default:
                            return false;
                    }
                },
                addValidator: function(el, setting){
                    if (debugMode) {
                        console.log('-> addValidator() with Element[id="' + el.id + '"][name="' + el.name + '"]');
                    }
                    if (el.disabled) {
                        if (debugMode) {
                            console.info('Element[id="' + el.id + '"][name="' + el.name + '"] is disabled!');
                        }
                    }
                    var jQElement = $(el);
                    var initCarrier = window.MICE.control.elementIsValid(el, true);
                    window.MICE.view.initTipContainer(initCarrier);
                    window.MICE.view.showMessage(initCarrier);
                    var defaultValue = setting.defaultValue;
                    if (defaultValue) {
                        jQElement.val(defaultValue);
                    }
                    var sTag = el.tagName;
                    var sType = el.type;
                    if ('INPUT' == sTag || 'TEXTAREA' == sTag || 'SELECT' == sTag) {
                        jQElement.bind('focus', function(){
                            var carrier = $.extend({}, initCarrier);
                            carrier.tipContent = carrier.el.settings[0].onFocusTip;
                            carrier.validState = 'onFocus';
                            window.MICE.view.showMessage(carrier);
                        });
                        if (sTag == 'SELECT') {
                            jQElement.bind('blur', function(){
                                $(this).trigger('change');
                            });
                            jQElement.bind('keyup', function(){
                                $(this).trigger('change');
                            });
                            jQElement.bind('change', function(){
                                var carrier = window.MICE.control.elementIsValid(this);
                                window.MICE.view.showMessage(carrier);
                            });
                        }
                        else {
                            jQElement.bind(setting.triggerOccasion, function(){
                                var carrier = window.MICE.control.elementIsValid(this);
                                window.MICE.view.showMessage(carrier);
                                if (!carrier.isValid) {
                                    var sType = this.type;
                                    if (setting.autoModify && ('text' == sType || 'textarea' == sType || 'file' == sType)) {
                                        // TODO
                                        window.MICE.control.doAutoModify(carrier);
                                        window.MICE.view.showMessage(initCarrier);
                                    }
                                    else {
                                        if (setting.forceValid) {
                                            this.focus();
                                        }
                                    }
                                }
                            });
                        }
                    }
                    return true;
                },
                removeValidator: function(el){
                    if (debugMode) {
                        console.log('-> removeValidator() with Element[id="' + el.id + '"][name="' + el.name + '"]');
                    }
                    var setting0 = el.settings[0];
                    setting0.bind = false;
                    if ('tip' == setting0.tipType && 0 != $(setting0.tipSelector.split(' ')[0]).find(setting0.tipSelector.split(' ')[1]).length) {
                        $(setting0.tipSelector.split(' ')[0]).find(setting0.tipSelector.split(' ')[1]).remove();
                    }
                    $(el).unbind('focus').unbind('blur').unbind('change').unbind(setting0.triggerOccasion);
                },
                toggleValidator: function(el, isActive){
                    if (debugMode) {
                        console.log('-> toggleValidator() with Element[id="' + el.id + '"][name="' + el.name + '"]');
                    }
                    var setting0 = el.settings[0];
                    if (isActive === undefined) {
                        setting0.bind = !setting0.bind;
                    }
                    else {
                        if (isActive) {
                            setting0.bind = true;
                        }
                        else {
                            setting0.bind = false;
                        }
                    }
                },
                formIsValid: function(validatorForm){
                    if (!validatorForm) {
                        return false;
                    }
                    var theControl = this;
                    var isValid = true;
                    var firstErrorElement = '';
                    var formSetting = window.MICE.control.getConfig(validatorForm);
                    if (debugMode) {
                        console.info('Validate <' + formSetting.validatorForm + '> Begin:');
                    };
                    var validElements = $(validatorForm).find(':input');
                    validElements.each(function(i, el){
                        if (!!el.settings && !!el.settings[0].bind) {
                            var sType = el.type;
                            var needValidate = false;
                            if ('checkbox' == sType || 'radio' == sType) {
                                needValidate = 0 <= $(':input[name="' + el.name + '"]:last').index(el);
                            }
                            else {
                                needValidate = true;
                            }
                            if (!needValidate) {
                                return;
                            }
                            var carrier = window.MICE.control.elementIsValid(el);
                            if (!carrier.isValid) {
                                isValid = false;
                                window.MICE.view.showMessage(carrier);
                                //Deal with submit while Ajax Request
                                if (carrier.validState == 'onWait' && !firstErrorElement) {
                                    theControl.needResubmit = true;
                                }
                                else {
                                    if (!firstErrorElement) {
                                        firstErrorElement = el;
                                    }
                                    theControl.needResubmit = false;
                                }
                            }
                        }
                    });
                    if (debugMode) {
                        var cLog = 'Validate <' + formSetting.validatorForm + '> ' + (isValid ? 'Passed.' : 'Failed!');
                        isValid ? console.info(cLog) : console.warn(cLog);
                    };
                    if (isValid) {
                        if ('function' == typeof(formSetting.passCallback)) {
                            isValid = !!formSetting.passCallback();
                        }
                        if (formSetting.submitOnce) {
                            $(':input[type="submit"],:input[type="image"]').attr('disabled', true);
                        }
                    }
                    else {
                        if ('function' == typeof(formSetting.failCallback)) {
                            isValid = !!formSetting.failCallback();
                        }
                        if (!isValid && '' != firstErrorElement && formSetting.setFocus) {
                            window.scrollTo(0, $(firstErrorElement).eq(0).offset().top - 200);
                        }
                    }
                    return !formSetting.debug && isValid;
                },
                formValidator: function(){
                    var formSelector = $(this);
                    var originValue = true;
                    var originSubmitListener = $(formSelector).onsubmit;
                    if ('function' == typeof(originSubmitListener)) {
                        originValue = originSubmitListener();
                    }
                    ajaxAsync = false; //修复提交时对ajax校验不能异步正确取得校验结果，而误阻止表单提交BUG
                    var valid = window.MICE.control.formIsValid(formSelector);
                    ajaxAsync = true;
                    return originValue && valid;
                },
                formUnbind: function(boolUnbind){
                    var allForms = $('form[class*=ppt.]');
                    for (var i = 0, j = allForms.length; i < j; i++) {
                        var validatorForm = allForms.get(i);
                        var classNames = $(validatorForm).attr('class').split(' ');
                        for (var k = 0, l = classNames.length; boolUnbind && k < l; k++) {
                            if (classNames[k].indexOf('ppt.') >= 0) {
                                if (debugMode) {
                                    console.info('Unbind: formSelector<' + validatorForm.name + '>.');
                                };
                                var validElements = $(validatorForm).find(':input');
                                validElements.each(function(i, el){
                                    if (!!el.settings && !!el.settings[0].bind) {
                                        window.MICE.control.removeValidator(el);
                                    }
                                });
                                $(validatorForm).unbind("submit", window.MICE.control.formValidator);
                                break;
                            }
                        }
                    }
                },
                // Check for one element and return carrier, with settings[0] on true while settings[i] on false
                elementIsValid: function(el, isInit){
                    var carrier = new Object();
                    carrier.el = el;
                    var validatorForm = $(el).parentsUntil("form").parent();
                    carrier.formEl = validatorForm; //对于页面多个form表单校验，同时相互存在name相同元素，进行处理 
                    var settings = el.settings;
                    carrier.tipSelector = "form[class='" + validatorForm.attr('class') + "'] " + settings[0].tipSelector;
                    carrier.tipContent = '';
                    carrier.validState = '';
                    if (!settings[0].bind) {
                        carrier.setting = settings[0];
                        return carrier;
                    }
                    for (var i = 0, j = settings.length; i < j; i++) {
                        carrier.setting = settings[i];
                        if (el.disabled) {
                            carrier.isValid = true;
                            break;
                        }
                        if (i == 0) {
                            if (settings[0].empty && 0 == window.MICE.control.getRange(el)) {
                                carrier.isValid = true;
                                carrier.tipContent = settings[0].onEmptyTip || settings[0].onShowTip;
                                carrier.validState = 'onShow';
                                break;
                            }
                            if (!!isInit) {
                                break;
                            }
                            carrier.isValid = true;
                            continue;
                        }
                        window.MICE.control.triggerValidation(carrier);
                        if (!!settings[i].isValid) {
                            carrier.setting = settings[0];
                            carrier.isValid = true;
                            carrier.tipContent = settings[0].onPassTip;
                            carrier.validState = 'onPass';
                        }
                        else {
                            carrier.isValid = false;
                            if ('AjaxValidator' == settings[i].validateType && '' == settings[i].lastValue) {
                                carrier.tipContent = carrier.setting.onWaitTip || settings[0].onWaitTip;
                                carrier.validState = 'onWait';
                            }
                            else {
                                if (debugMode) {
                                    console.info('Validate Element[id="' + el.id + '"][name="' + el.name + '"] Failed!');
                                };
                                carrier.tipContent = carrier.setting.onFailTip || settings[0].onFailTip;
                                carrier.validState = 'onError';
                            }
                            break;
                        }
                    }
                    return carrier;
                },
                getRange: function(el){
                    var sType = el.type;
                    var len = 0;
                    switch (sType) {
                        case 'text':
                        case 'hidden':
                        case 'password':
                        case 'textarea':
                        case 'file':
                            var val = $(el).val();
                            var formSetting = window.MICE.control.getConfig(el.settings[0].validatorForm);
                            if (!formSetting.wideWord) {
                                for (var i = 0, j = val.length; i < j; i++) {
                                    var k = val.charCodeAt(i);
                                    if (k >= 0x0391 && k <= 0xFFE5) {//根据GBK编码计算字节数（旧：k >= 0x4e00 && k <= 0x9fa5）
                                        len += 2;
                                    }
                                    else {
                                        len++;
                                    }
                                }
                            }
                            else {
                                len = val.length;
                            }
                            break;
                        case 'checkbox':
                        case 'radio':
                            len = $('input[type="' + sType + '"][name="' + el.name + '"]:checked').length;
                            break;
                        case 'select-one':
                            len = el.options ? el.options.selectedIndex : -1;
                            break;
                        case 'select-multiple':
                            len = $('select[name=' + el.name + '] option:selected').length;
                            break;
                    }
                    return len;
                },
                triggerValidation: function(carrier){
                    if (debugMode) {
                        console.log('== Validate Element[name="' + carrier.el.name + '"] | Type: ' + carrier.setting.validateType);
                    };
                    switch (carrier.setting.validateType) {
                        case 'RangeValidator':
                            window.MICE.control.rangeValid(carrier);
                            break;
                        case 'PatternValidator':
                            window.MICE.control.patternValid(carrier);
                            break;
                        case 'CallbackValidator':
                            window.MICE.control.callbackValid(carrier);
                            break;
                        case 'CompareValidator':
                            window.MICE.control.compareValid(carrier);
                            break;
                        case 'AjaxValidator':
                            window.MICE.control.ajaxValid(carrier);
                            break;
                    }
                },
                rangeValid: function(carrier){
                    var setting = carrier.setting;
                    setting.isValid = true;
                    var el = carrier.el;
                    if (!el.settings[0].bind) {
                        return;
                    }
                    var jQElement = $(el);
                    var val = jQElement.val();
                    var sType = el.type;
                    var len = window.MICE.control.getRange(el);
                    switch (sType) {
                        case 'text':
                        case 'hidden':
                        case 'password':
                        case 'textarea':
                        case 'file':
                            if ('value' == setting.dataType) {
                                setting.isValid = val >= setting.min && val <= setting.max;
                            }
                            if ('length' == setting.dataType && !isNaN(setting.min) && !isNaN(setting.max)) {
                                setting.isValid = len >= setting.min && len <= setting.max;
                            }
                            if ('date' == setting.dataType) {
                            // TODO
                            }
                            break;
                        case 'select-one':
                            if ('value' == setting.dataType && !isNaN(setting.min) && !isNaN(setting.max)) {
                                setting.isValid = len >= 0 && val >= setting.min && val <= setting.max;
                            }
                            break;
                        case 'checkbox':
                        case 'radio':
                        case 'select-multiple':
                            if ('size' == setting.dataType && !isNaN(setting.min) && !isNaN(setting.max)) {
                                setting.isValid = len >= setting.min && len <= setting.max;
                            }
                            break;
                    }
                    return;
                },
                patternValid: function(carrier){
                    var setting = carrier.setting;
                    setting.isValid = true;
                    var el = carrier.el;
                    if (!el.settings[0].bind) {
                        return;
                    }
                    var jQElement = $(el);
                    var val = jQElement.val();
                    var sType = el.type;
                    var sTag = el.tagName;
                    if (el.settings[0].empty && '' == val) {
                        return;
                    }
                    else {
                        var regexRule = setting.regExp;
                        if ('enum' == setting.dataType && !!regexRule) {
                            regexRule = window.MICE.rule.pattern[regexRule];
                        }
                        if (!regexRule) {
                            return;
                        }
                        var result = (new RegExp(regexRule, setting.regParam)).test(val);
                        setting.isValid = setting.reverse ? !result : result;
                    }
                    return;
                },
                callbackValid: function(carrier){
                    var setting = carrier.setting;
                    setting.isValid = true;
                    var el = carrier.el;
                    if (!el.settings[0].bind) {
                        return;
                    }
                    var jQElement = $(el);
                    var val = jQElement.val();
                    if ('function' == typeof(setting.callback)) {
                        var callback = setting.callback(val, el);
                        if ('string' == typeof(callback)) {
                            setting.isValid = false;
                            setting.onFailTip = callback;
                        }
                        else {
                            setting.isValid = !!callback;
                        }
                    }
                    else {
                        var callback = window.MICE.rule.func[setting.callback] || window[setting.callback];
                        setting.isValid = 'function' == typeof(callback) ? !!callback(val, el) : true;
                    }
                    return;
                },
                compareValid: function(carrier){
                    var setting = carrier.setting;
                    setting.isValid = true;
                    var el = carrier.el;
                    if (!el.settings[0].bind) {
                        return;
                    }
                    var jQElement = $(el);
                    var curVal = jQElement.val();
                    var elTarget = $('#' + setting.targetSelector.replace('#', ''));
                    if (0 == elTarget.length) {
                        elTarget = $(':input[name="' + setting.targetSelector + '"]');
                        if (0 == elTarget.length) {
                            return;
                        }
                    }
                    var desVal = elTarget.val();
                    switch (setting.operator) {
                        case 'eq':
                            if (curVal != desVal) {
                                setting.isValid = false;
                            }
                            break;
                        case 'ne':
                            if (curVal == desVal) {
                                setting.isValid = false;
                            }
                            break;
                        case 'le':
                            if (curVal > desVal) {
                                setting.isValid = false;
                            }
                            break;
                        case 'ge':
                            if (curVal < desVal) {
                                setting.isValid = false;
                            }
                            break;
                        case 'lt':
                            if (curVal >= desVal) {
                                setting.isValid = false;
                            }
                            break;
                        case 'gt':
                            if (curVal <= desVal) {
                                setting.isValid = false;
                            }
                            break;
                    }
                    return;
                },
                ajaxValid: function(carrier){
                    var theControl = this;
                    var el = carrier.el;
                    if (!el.settings[0].bind) {
                        carrier.setting.isValid = true;
                        return;
                    }
                    var jQElement = $(el);
                    var setting = carrier.setting;
                    var async = setting.async;
                    if (ajaxAsync != null) {
                        async = ajaxAsync && async;
                    }
                    $.ajax({
                        mode: 'abort',
                        type: setting.type,
                        url: setting.url,
                        cache: setting.cache,
                        data: (typeof setting.data == 'function' ? setting.data() : setting.data) + '&' + jQElement.serialize(),
                        async: async,
                        dataType: setting.dataType,
                        processData: setting.processData,
                        success: function(responseData){
                            if ('function' == typeof(setting.success)) {
                                var res = setting.success(responseData);
                                if ('string' == typeof res) {
                                    setting.isValid = false;
                                    setting.onFailTip = res;
                                }
                                else {
                                    setting.isValid = !!res;
                                }
                            }
                            else {
                                setting.isValid = setting.passFlag == responseData;
                            }
                            setting.lastValue = jQElement.val();
                            jQElement.triggerHandler(el.settings[0].triggerOccasion);
                        },
                        complete: function(){
                            $(':input[type="submit"],:input[type="image"]').attr('disabled', false);
                            if ('function' == typeof(setting.complete)) {
                                setting.complete();
                            }
                            if (theControl.needResubmit && setting.isValid) {
                                //$(setting.validatorForm).trigger('submit');  取消最后一个ajax校验成功后自动提交功能
                            }
                        },
                        beforeSend: function(xhr){
                            var val = jQElement.val();
                            if (setting.lastValue != val && '' != val) {
                                if ('function' == typeof(setting.beforeSend)) {
                                    setting.beforeSend(xhr);
                                }
                                $(':input[type="submit"],:input[type="image"]').attr('disabled', true);
                                setting.isValid = false;
                                setting.lastValue = '';
                                return true;
                            }
                            else {
                                if ('' == val) {
                                    setting.isValid = true; //对第一次为空时，取消ajax校验且不提示错误
                                }
                                return false;
                            }
                        },
                        error: function(){
                            setting.isValid = false;
                            setting.lastValue = null;
                            jQElement.triggerHandler(el.settings[0].triggerOccasion);
                            if ('function' == typeof(setting.error)) {
                                setting.error();
                            }
                        }
                    });
                },
                doAutoModify: function(carrier){
                    // TODO
                }
            },
            //---------------------------------------------------//
            /**
             * @namespace view
             */
            view: {
                initTipContainer: function(carrier){
                    var createTipContainer = function(carrier){
                        if (0 == $(carrier.tipSelector.split(' ')[0]).find(carrier.tipSelector.split(' ')[1]).length) {
                            if (debugMode) {
                                console.log('-> createTipContainer() with tipSelector<' + carrier.tipSelector + '>');
                            }
                            var sType = carrier.el.type;
                            if ('checkbox' == sType || 'radio' == sType) {
                                var jQElement = $(':input[name="' + carrier.el.name + '"]:last');
                                var JQParentElement = 0 < jQElement.parents('td').length ? jQElement.parents('td') : jQElement.parents('div');
                                JQParentElement.append('<div id="' + carrier.tipSelector.split(' ')[1].replace('#', '') + '" style="display:none;"></div>');
                            }
                            else {
                                var jQElement = $(carrier.el);
                                jQElement.parent().append('<div id="' + carrier.tipSelector.split(' ')[1].replace('#', '') + '" style="display:none;"></div>');
                            }
                        }
                        $(carrier.tipSelector.split(' ')[0]).find(carrier.tipSelector.split(' ')[1]).css(carrier.el.settings[0].tipCSS);
                    };
                    var tType = carrier.setting.tipType || carrier.el.settings[0].tipType;
                    switch (tType) {
                        case 'tip':
                            createTipContainer(carrier);
                            break;
                    }
                },
                showAlert: function(tipSelector, validState, tipContent){
                    if (!!tipContent) {
                        alert(tipContent);
                    }
                },
                showTip: function(tipSelector, validState, tipContent, showClass){
                    var tip = $(tipSelector.split(' ')[0]).find(tipSelector.split(' ')[1]);
                    tip.removeClass().addClass(showClass || validState).html(tipContent);
                    if (!tipContent) {
                        tip.hide();
                    }
                    else {
                        //修改原先的"tip.show()"，原因：show相当于设置display为block，不方便后期样式调整
                        tip.attr("style", "display:''");
                    }
                },
                showDialog: function(tipSelector, validState, tipContent){
                    if (tipContent.length <= 0) {
                        return;
                    };
                    switch (validState) {
                        case 'onShow':
                        case 'onWait':
                        case 'onFocus':
                        case 'onEmpty':
                            window.MICE.view.modaldialog.focus(tipContent);
                            break;
                        case 'onWarning':
                            window.MICE.view.modaldialog.warning(tipContent);
                            break;
                        case 'onPrompt':
                            window.MICE.view.modaldialog.prompt(tipContent);
                            break;
                        case 'onConfirm':
                            window.MICE.view.modaldialog.confirm(tipContent);
                            break;
                        case 'onPass':
                            window.MICE.view.modaldialog.success(tipContent);
                            break;
                        case 'onError':
                            window.MICE.view.modaldialog.error(tipContent);
                            break;
                    }
                },
                showMessage: function(carrier){
                    var tType = carrier.setting.tipType || carrier.el.settings[0].tipType;
                    switch (tType) {
                        case 'alert':
                            window.MICE.view.showAlert(carrier.tipSelector, carrier.validState, carrier.tipContent);
                            break;
                        case 'tip':
                            window.MICE.view.showTip(carrier.tipSelector, carrier.validState, carrier.tipContent, carrier.setting.showClass);
                            break;
                        case 'dialog':
                            window.MICE.view.showDialog(carrier.tipSelector, carrier.validState, carrier.tipContent);
                            break;
                        // TODO YFT
                    }
                },
                modaldialog: {
                    DialogTypes: new Array("error", "warning", "success", "prompt", "confirm", "focus"),
                    DialogTitles: {
                        "error": "Error!!",
                        "warning": "Warning!",
                        "success": "Success.",
                        "prompt": "Please Choose",
                        "confirm": "Confirm?",
                        "focus": "Attention:"
                    },
                    defaults: {
                        timeout: 0,
                        showClose: true,
                        width: 525
                    },
                    error: function(msg, options){
                        if (typeof(options) == "undefined") {
                            options = {};
                        }
                        options['type'] = "error";
                        return (window.MICE.view.modaldialog.showDialog(msg, options));
                    },
                    focus: function(msg, options){
                        if (typeof(options) == "undefined") {
                            options = {};
                        }
                        options['type'] = "focus";
                        return (window.MICE.view.modaldialog.showDialog(msg, options));
                    },
                    hide: function(msg, options){
                        $('#dialog').hide(0);
                        $('#dialog-mask').hide(0);
                        $(document).unbind('keydown');
                    },
                    prompt: function(msg, options){
                        if (typeof(options) == "undefined") {
                            options = {};
                        }
                        options['type'] = "prompt";
                        return (window.MICE.view.modaldialog.showDialog(msg, options));
                    },
                    success: function(msg, options){
                        if (typeof(options) == "undefined") {
                            options = {};
                        }
                        options['type'] = "success";
                        return (window.MICE.view.modaldialog.showDialog(msg, options));
                    },
                    warning: function(msg, options){
                        if (typeof(options) == "undefined") {
                            options = {};
                        }
                        options['type'] = "warning";
                        return (window.MICE.view.modaldialog.showDialog(msg, options));
                    },
                    confirm: function(msg, options){
                        if (typeof(options) == "undefined") {
                            options = {};
                        }
                        options['type'] = "confirm";
                        return (window.MICE.view.modaldialog.showDialog(msg, options));
                    },
                    showDialog: function(msg, options){
                        if (!$.inArray(options.type, window.MICE.view.modaldialog.DialogTypes)) {
                            options.type = window.MICE.view.modaldialog.DialogTypes[0];
                        };
                        var settings = $.extend({
                            title: window.MICE.view.modaldialog.DialogTitles[options.type]
                        }, window.MICE.view.modaldialog.defaults, options);
                        settings.timeout = (typeof(settings.timeout) == "undefined") ? 0 : settings.timeout;
                        settings.showClose = ((typeof(settings.showClose) == "undefined") | !settings.timeout) ? true : !!settings.showClose;
                        if (!document.getElementById('dialog')) {
                            var dialog = document.createElement('div');
                            dialog.id = 'dialog';
                            $(dialog).html("<div id='dialog-header'>" +
                            "<div id='dialog-title'></div>" +
                            "<div id='dialog-close'></div>" +
                            "</div>" +
                            "<div id='dialog-content'>" +
                            "<div id='dialog-content-inner' />" +
                            "<div id='dialog-button-container'>" +
                            "<input type='button' id='dialog-button-ok' value='OK'>" +
                            "<input type='button' id='dialog-button' value='Cancel'>" +
                            "</div>" +
                            "</div>");
                            $(dialog).hide();
                            var dialogmask = document.createElement('div');
                            dialogmask.id = 'dialog-mask';
                            $(dialogmask).hide();
                            document.body.appendChild(dialog);
                            document.body.appendChild(dialogmask);
                            $("#dialog-close").click(window.MICE.view.modaldialog.hide);
                            $("#dialog-button").click(window.MICE.view.modaldialog.hide);
                            if ($.browser.msie && 7 > $.browser.version.split('.')[0]) {
                                var maskHeight = document.body.clientHeight;
                                if (window.screen.availHeight > document.body.clientHeight) {
                                    maskHeight = window.screen.availHeight;
                                }
                                $(dialogmask).css({
                                    'position': 'absolute',
                                    'height': maskHeight + 'px',
                                    'filter': 'Alpha(Opacity=40)'
                                }).before('<iframe class="zIndexIframe" frameborder="0" style="filter:Alpha(Opacity=0);position:absolute;left:expression(this.nextSibling.offsetLeft);top:expression(this.nextSibling.offsetTop);width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);"></iframe>');
                                $(dialog).css('position', 'absolute');
                                window.popMaskInterval = setInterval(function(){
                                    var objdiv = dialog;
                                    var clientWidth = parseInt(objdiv.clientWidth);
                                    var clientHeight = parseInt(objdiv.clientHeight);
                                    var objLeft = parseInt((document.documentElement.scrollLeft || document.body.scrollLeft) + (document.documentElement.clientWidth - clientWidth) / 2) + "px";
                                    var objTop = parseInt((document.documentElement.scrollTop || document.body.scrollTop) + (document.documentElement.clientHeight - clientHeight) / 2) + "px";
                                    objdiv.style.left = objLeft;
                                    objdiv.style.top = objTop;
                                }, 50);
                            }
                        }
                        var dl = $('#dialog');
                        var dlh = $('#dialog-header');
                        var dlc = $('#dialog-content');
                        var dlb = $('#dialog-button');
                        var dlbo = $('#dialog-button-ok');
                        $('#dialog-title').html(settings.title);
                        $('#dialog-content-inner').html(msg);
                        dl.css('width', settings.width);
                        var dialogTop = Math.abs($(window).height() - dl.height()) / 2;
                        dl.css('left', ($(window).width() - dl.width()) / 2);
                        dl.css('top', (dialogTop >= 25) ? dialogTop : 25);
                        $.each(window.MICE.view.modaldialog.DialogTypes, function(){
                            dlh.removeClass(this + "header");
                        });
                        dlh.addClass(settings.type + "header");
                        $.each(window.MICE.view.modaldialog.DialogTypes, function(){
                            dlc.removeClass(this);
                        });
                        dlc.addClass(settings.type);
                        $.each(window.MICE.view.modaldialog.DialogTypes, function(){
                            dlb.removeClass(this + "button");
                        });
                        dlb.addClass(settings.type + "button");
                        $.each(window.MICE.view.modaldialog.DialogTypes, function(){
                            dlbo.removeClass(this + "button");
                        });
                        dlbo.addClass(settings.type + "button");
                        if (!settings.showClose) {
                            $('#dialog-close').hide();
                            $('#dialog-button-container').hide();
                        }
                        else {
                            $('#dialog-close').show();
                            $('#dialog-button-container').show();
                        }
                        if (settings.type == window.MICE.view.modaldialog.DialogTypes[3] || settings.type == window.MICE.view.modaldialog.DialogTypes[4]) {
                            dlbo.show();
                            dlbo.click(window.MICE.view.modaldialog.hide);
                        }
                        else {
                            dlbo.hide();
                        }
                        if (settings.timeout) {
                            window.setTimeout("$('#dialog').fadeOut('slow', 0); $('#dialog-mask').fadeOut('normal', 0);", (settings.timeout * 1000));
                        }
                        dl.fadeIn("fast");
                        $('#dialog-mask').fadeTo("fast", 0.4, function(){
                            if ($.browser.msie && 7 <= $.browser.version.split('.')[0]) {
                                $(this).removeAttr('style');
                            }
                        });
                        $(document).bind('keydown', function(e){
                            switch (e.keyCode) {
                                case 27 || 'ESC':
                                    window.MICE.view.modaldialog.hide();
                                    break;
                                default:
                                    return false;
                            }
                        });
                    }
                }
            },
            //---------------------------------------------------//
            /**
             * @namespace rule
             */
            rule: {
                // Global settings
                globalConfig: {
                    // Debug mode, never submit forms
                    debug: false,
                    // Selector of one validator group
                    validatorForm: 'form0',
                    // passCallback()
                    passCallback: function(){
                        if ('function' == typeof(window.passCallFun)) {
                            return passCallFun();
                        }
                        return true;
                    },
                    // failCallback()
                    failCallback: function(){
                        if ('function' == typeof(window.failCallFun)) {
                            return failCallFun();
                        }
                        return false;
                    },
                    //
                    autoTips: true,
                    //
                    forceValid: false,
                    // Block on form submit
                    submitOnce: false,
                    // Less tips
                    tidyMode: false,
                    // Set focus while invalid
                    setFocus: true,
                    // CJK char as one letter
                    wideWord: false,
                    // Val: onShow onFocus onEmpty onWarning onPrompt onPass onError onConfirm
                    validState: ''
                },
                // Group setting
                initSetting: {
                    //
                    validatorForm: '',
                    //
                    validateType: 'InitValidator',
                    // Val: blur, focus, click, change
                    triggerOccasion: 'blur',
                    //
                    tipSelector: '',
                    //
                    onShowTip: '',
                    //
                    onFocusTip: '',
                    //
                    onEmptyTip: '',
                    //
                    onPassTip: '',
                    //
                    onFailTip: '',
                    // Validate instantly
                    bind: true,
                    //
                    defaultValue: null,
                    //
                    autoModify: false,
                    //
                    forceValid: false,
                    // Not required
                    empty: false,
                    // Val: alert, tip, dialog, YFT
                    tipType: 'tip',
                    //
                    tipCSS: {}
                },
                rangeSetting: {
                    validateType: 'RangeValidator',
                    onFailTip: '',
                    min: 0,
                    max: Number.POSITIVE_INFINITY,
                    wideWord: false,
                    // Val: length, value, size, date, time, datetime
                    dataType: 'length',
                    isValid: true
                },
                patternSetting: {
                    validateType: 'PatternValidator',
                    onFailTip: '',
                    // Val: enum, string
                    dataType: 'enum',
                    regExp: '',
                    regParam: 'i',
                    reverse: false,
                    isValid: true
                },
                callbackSetting: {
                    validateType: 'CallbackValidator',
                    onFailTip: '',
                    //callback(value, el)
                    callback: null,
                    isValid: true
                },
                compareSetting: {
                    validateType: 'CompareValidator',
                    onFailTip: '',
                    targetSelector: '',
                    // Val: string, number, date, time, datetime
                    dataType: 'string',
                    // Val: eq, ne, le, ge, lt, gt
                    operator: 'eq',
                    isValid: false
                },
                ajaxSetting: {
                    validateType: 'AjaxValidator',
                    onFailTip: '',
                    onWaitTip: '',
                    lastValue: null,
                    type: 'GET',
                    url: '',
                    data: '',
                    async: true,
                    cache: true,
                    dataType: 'text',
                    passFlag: true,
                    processData: true,
                    // beforeSend(xhr)
                    beforeSend: null,
                    // success(responseData)
                    success: null,
                    // error()
                    error: null,
                    // complete()
                    complete: null,
                    isValid: false
                },
                pattern: {
                    integer: "^(-?[1-9]\\d*)?$",
                    integer1: "^(\\+?[1-9]\\d*)?$",
                    integer2: "^(-[1-9]\\d*)?$",
                    integer3: "^(\\d*)?$",
                    num: "^(([+-]?)\\d*\\.?\\d+)?$",
                    num1: "^(\\+?[1-9]\\d*|0)?$",
                    num2: "^(-[1-9]\\d*|0)?$",
                    decimal: "^(([+-]?)\\d*\\.\\d+)?$",
                    decimal1: "^([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)?$",
                    decimal2: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))?$",
                    decimal3: "^(-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0))?$",
                    decimal4: "^([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)?$",
                    decimal5: "^((-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0)?$",
                    email: "^(\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+)?$",
                    color: "^[a-fA-F0-9]{6}$",
                    url: "^(([a-zA-z]+://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*).*)*(\\?\\S*)?)?$",
                    chinese: "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]|+$",
                    notchinese: "^[\\x00-\\xFF]+$",
                    textareacheck: "^[\\x00-\\xFF\\u20ac\\u2122\\u3b1\\u3b2\\u3b3\\u3b4\\u3b7\\u3b8\\u3bb\\u3bc\\u3bd\\u3be\\u3c0\\u3c3\\u3c4\\u3c1\\u3c6\\u3c7\\u3c9\\u394\\u3a3\\u3a6\\u3a9\\u2014\\u2264\\u2265\\u2208\\u2030\\u2260\\u2228\\u2227\\u2211\\u221e\\u2261\\u221a\\u2018\\u20ac\\u2122\\u3b1\\u3b2\\u3b3\\u3b4\\u3b7\\u3b8\\u3bb\\u3bc\\u3bd\\u3be\\u3c0\\u3c3\\u3c4\\u3c1\\u3c6\\u3c7\\u3c9\\u394\\u3a3\\u3a6\\u3a9\\u2014\\u2264\\u2265\\u2208\\u2030\\u2260\\u2228\\u2227\\u2211\\u221e\\u2261\\u221a\\u2018]+$",
                    asciicode: "^[\\x00-\\xFF]+$",
                    zipcode: "^(\\d{6})?$",
                    mobile: "^((13|15|18)[0-9]{9})?$",
                    ip4: "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$",
                    notempty: "\\S",
                    picture: "(.*)\\.(jpg|gif|jpeg)$",
                    rar: "(.*)\\.(rar|zip|7zip|tgz)$",
                    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",
                    qq: "^([0-9]*)?$",
                    tel: "(\\d{3}-|\\d{4}-)?(\\d{8}|\\d{7})",
                    username: "^(\\w+)?$",
                    letter: "^([A-Za-z]+)?$",
                    letter_u: "^([A-Z]+)?$",
                    letter_l: "^([a-z]+)?$",
                    password: "^([a-zA-Z0-9\-]{6,20})?$",
                    micmobile: "^([a-zA-Z0-9].*)?$",
                    micusername: "^([a-zA-Z0-9\-]+)?$",
                    required: "\\S+",
                    telephone: "^([0-9\-]+)?$",
                    insusername: "^([a-zA-Z0-9\-]{6,20})?$",
                    insdate: "^((?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29))?$",
                    idcode: "^((\\d{6})(18|19|20)(\\d{2})((0\\d)|1[012])(([012]\\d)|3[01])(\\d{3})(\\d|X|x)?)?$|^((\\d{6})(\\d{2})((0\\d)|1[012])(([012]\\d)|3[01])(\\d{3}))?$", //身份证
                    insmobile: "^((13[0-9]|147|15[0|1|2|3|5|6|7|8|9]|18[6|7|8|9])\\d{8})?$",
                    inspassword: "^(([a-zA-Z0-9\-]){6,20})?$",
                    instel: "^((\\d{3}-?|\\d{4}-?)?(\\d{8}|\\d{7}))?$",
                    insanytel: "^((\\d|-)*)?$",
                    insmoney: "^(0\\.\\d?[1-9])$|^([0-9]\\d*)(\\.\\d{1,2})?$",
                    insmoney1: "^(0(\\.\\d{1,2})?)$|^([1-9]\\d{0,7})(\\.\\d{1,2})?$",
                    instextarea: "^[\\u4e00-\\u9fa5\\a-zA-Z0-9]{1,500}$",
                    insrebate: "^([0-1](\\.\\d{1,2})?)?$",
                    inschargefee: "^((\\d|[1-9]\\d{0,2})?(\\.\\d{1,2})?)?$"
                },
                func: {
                    test: function(el){
                        return !!el;
                    }
                }
            }
        };
        if (!window.MICE) {
            window.MICE = {};
        }
        $.extend(window.MICE, miceValidator);
        window.MICE.control.init();
        var cssValidator = document.createElement("link");
        cssValidator.setAttribute("type", "text/css");
        cssValidator.setAttribute("rel", "stylesheet");
        cssValidator.setAttribute("href", miceBaseURI + "mice/validator.css");
        document.getElementsByTagName("head").item(0).appendChild(cssValidator);
        //window.setTimeout(function(){window.MICE.control.formUnbind(true);if(window.console){console.warn('All forms are unbound!');}}, 5000);
    })(jQuery);
}
