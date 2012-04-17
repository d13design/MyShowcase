var MyShowcaseFancyBox = {
	// Initialise Fancybox
		init: (function() {

		    $("#url").fancybox({
		    	'width'				: 480,
		    	'height'			: 620,
		    	'autoScale'			: false,
		    	'centerOnScroll'	: true,
		    	'transitionIn'		: 'none',
		    	'transitionOut'		: 'none',
		    	'type'				: 'iframe',
		    	'modal'				: true,
		    	'onStart'			: function () {
		    								MyShowcaseArtefact.setChain("WebLink","New");
		    							},
		    	'onClosed'			: function () {
		    								MyShowcaseArtefact.resetEvidenceStream() ;
		    							}
		    });
			$("#fileupload").fancybox({
		    	'width'				: 480,
		    	'height'			: 520,
		    	'autoScale'			: false,
		    	'centerOnScroll'	: true,
		    	'transitionIn'		: 'none',
		    	'transitionOut'		: 'none',
		    	'type'				: 'iframe',
		    	'modal'				: true,
		    	'onStart'			: function () {
											MyShowcaseArtefact.setChain("File","New");
										},
				'onClosed'			: function () {
											MyShowcaseArtefact.resetEvidenceStream() ;
										}
			});
			$("#rss").fancybox({
		    	'width'				: 480,
		    	'height'			: 560,
		    	'autoScale'			: false,
		    	'centerOnScroll'	: true,
		    	'transitionIn'		: 'none',
		    	'transitionOut'		: 'none',
		    	'type'				: 'iframe',
		    	'modal'				: true,
		    	'onStart'			: function () {
											MyShowcaseArtefact.setChain("RSS","New");
										},
				'onClosed'			: function () {
											MyShowcaseArtefact.resetEvidenceStream() ;
										}

			});
			$("#flickr").fancybox({
		    	'width'				: 480,
		    	'height'			: 670,
		    	'autoScale'			: false,
		    	'centerOnScroll'	: true,
		    	'transitionIn'		: 'none',
		    	'transitionOut'		: 'none',
		    	'type'				: 'iframe',
		    	'modal'				: true,
		    	'onStart'			: function () {
											MyShowcaseArtefact.setChain("Flickr","New");
										},
				'onClosed'			: function () {
											MyShowcaseArtefact.resetEvidenceStream() ;
										}

			});
			$("#twitter").fancybox({
		    	'width'				: 480,
		    	'height'			: 760,
		    	'autoScale'			: false,
		    	'centerOnScroll'	: true,
		    	'transitionIn'		: 'none',
		    	'transitionOut'		: 'none',
		    	'type'				: 'iframe',
		    	'modal'				: true,
		    	'onStart'			: function () {
											MyShowcaseArtefact.setChain("Twitter","New");
										},
				'onClosed'			: function () {
											MyShowcaseArtefact.resetEvidenceStream() ;
										}
			});
			$("#portfolio").fancybox({
		    	'width'				: 480,
		    	'height'			: 550,
		    	'autoScale'			: false,
		    	'centerOnScroll'	: true,
		    	'transitionIn'		: 'none',
		    	'transitionOut'		: 'none',
		    	'type'				: 'iframe',
		    	'modal'				: true,
		    	'onStart'			: function () {
											MyShowcaseArtefact.setChain("Portfolio","New");
										},
				'onClosed'			: function () {
											MyShowcaseArtefact.resetEvidenceStream() ;
										}
			});
			$("#vle").fancybox({
		    	'width'				: 480,
		    	'height'			: 500,
		    	'autoScale'			: false,
		    	'centerOnScroll'	: true,
		    	'transitionIn'		: 'none',
		    	'transitionOut'		: 'none',
		    	'type'				: 'iframe',
		    	'modal'				: true,
		    	'onStart'			: function () {
											MyShowcaseArtefact.setChain("Sakai","New");
										},
				'onClosed'			: function () {
											MyShowcaseArtefact.resetEvidenceStream() ;
										}
			});
		})
};
