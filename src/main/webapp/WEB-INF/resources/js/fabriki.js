function handlePollComplete(xhr, status, args) {
	if (document.getElementsByClassName('js_envioProcessando').length == 0) {
		PF('enviosPoller').stop();
	}
};

function hideDialogOnSuccess(args, dialogWidgetVar) {
    if (args && !args.validationFailed) {
        dialogWidgetVar.hide();
    }
};
