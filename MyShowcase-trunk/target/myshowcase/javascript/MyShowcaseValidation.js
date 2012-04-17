var Validation = {

   noDateEntered: (function(dt) {
	   var isInvalid = false ;

	   if (dt == null)
		   isInvalid = true ;
	   else
		   if (dt.length == 0)
			   isInvalid = true ;
	   return isInvalid ;
   }),

   invalidDateFormat: (function(dt) {
	   var isInvalid = false ;
	   if (dt.length != 0) {
		   var datearr = dt.split("/") ;
		   var formatOK = datearr.length ;
	   
		   if (formatOK != 3)
			   isInvalid = true ;
		   else {
			   var dateformat = /^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/
			   if (!dateformat.test(dt))
				   IsInvalid = true ;
		   }

	   }
	   return isInvalid ;
   }),

   invalidDate: (function(dt) {
	   var isInvalid = Validation.invalidDateFormat(dt) ;
	   
	   if (!isInvalid) {
		   var datearr = dt.split("/") ;
		   var dd = parseInt(datearr[0],10) ;
		   var mm = parseInt(datearr[1],10) ;
		   var yy = parseInt(datearr[2],10) ;
		   if ((dd < 0) || (dd > 31)) // Simple day check
			   isInvalid = true ;
		   else
			   if ((mm < 1) || (mm > 12)) // Simple month check
				   isInvalid = true ;
			   else { // More Complex stuff
				   var lastDay ;
				   switch (mm) {
					   case 1 :
						   lastDay = 31 ;
						   break ;
					   case 2 :
						   if ((((yy % 4) == 0) && (((yy % 100) != 0)) || ((yy % 400) == 0)))
							   lastDay = 29 ;
						   else
							   lastDay = 28 ;
						   break ;
					   case 3 :
						   lastDay = 31 ;
						   break ;
					   case 4 :
						   lastDay = 30 ;
						   break ;
					   case 5 :
						   lastDay = 31 ;
						   break ;
					   case 6 :
						   lastDay = 30 ;
						   break ;
					   case 7 :
						   lastDay = 31 ;
						   break ;
					   case 8 :
						   lastDay = 31 ;
						   break ;
					   case 9 :
						   lastDay = 30 ;
						   break ;
					   case 10 :
						   lastDay = 31 ;
						   break ;
					   case 11 :
						   lastDay = 30 ;
						   break ;
					   case 12 :
						   lastDay = 31 ;
						   break ;
				   }
				   if (dd > lastDay)
					   isInvalid = true ;
				   
			   }
	   }

	   return isInvalid ;
   }),

   dateInPast: (function(dt) {
	   var isInvalid = Validation.invalidDate(dt) ;
	   
	   if (!isInvalid) {
		   var datearr = dt.split("/") ;
		   var dd = parseInt(datearr[0],10) ;
		   var mm = parseInt(datearr[1],10) ;
		   var yy = parseInt(datearr[2],10) ;
		   var today = new Date() ;
		   today.setHours(0,0,0,0) ; // Strip time from date
		   var theDate = new Date(yy,mm-1,dd) ;
		   if (theDate < today)
			   isInvalid = true ;
	   }
	   return isInvalid ;
   }),

   endDateBeforeStartDate: (function(sdt, edt) {
	   var isInvalid = (Validation.invalidDate(sdt) || Validation.invalidDate(edt)) ;
	   if (!isInvalid) {
		   var sdatearr = sdt.split("/") ;
		   var sdd = parseInt(sdatearr[0],10) ;
		   var smm = parseInt(sdatearr[1],10) ;
		   var syy = parseInt(sdatearr[2],10) ;
		   var sDate = new Date(syy,smm-1,sdd) ;

		   var edatearr = edt.split("/") ;
		   var edd = parseInt(edatearr[0],10) ;
		   var emm = parseInt(edatearr[1],10) ;
		   var eyy = parseInt(edatearr[2],10) ;
		   var eDate = new Date(eyy,emm-1,edd) ;
		   if (eDate < sDate)
			   isInvalid = true ;
	   }
	   return isInvalid ;
   })

};
