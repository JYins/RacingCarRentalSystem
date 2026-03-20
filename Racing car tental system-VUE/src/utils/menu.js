const menu = {
	list() {
		return [{
			"backMenu": [{
				"child": [{
					"buttons": ["View", "Edit", "Delete"],
					"menu": "Account List",
					"menuJump": "List",
					"tableName": "user",
                    "icon": "iconfont icon-r-team"
				}],
				"menu": "Customer Accounts",
                "icon": "iconfont icon-r-team"
			}, {
				"child": [{
					"buttons": ["Add", "View", "Edit", "Delete"],
					"menu": "Location List",
					"menuJump": "List",
					"tableName": "dispenseArea",
                    "icon": "iconfont icon-r-building"
				}],
				"menu": "Pickup Locations",
                "icon": "iconfont icon-r-building"
			}, {
				"child": [{
					"buttons": ["Add", "View", "Edit", "Delete", "Deploy"],
					"menu": "Fleet Catalog",
					"menuJump": "List",
					"tableName": "carInfo",
                    "icon": "iconfont icon-r-shield"
				}],
				"menu": "Fleet Catalog",
                "icon": "iconfont icon-r-shield"
			}, {
				"child": [{
					"buttons": ["View", "Edit", "Delete"],
					"menu": "Active Fleet",
					"menuJump": "List",
					"tableName": "carDispense",
                    "icon": "iconfont icon-r-bottom"
				}],
				"menu": "Active Fleet",
                "icon": "iconfont icon-r-bottom"
			}, {
				"child": [{
					"buttons": ["Add", "View", "Edit", "Delete"],
					"menu": "Storage Queue",
					"menuJump": "List",
					"tableName": "carStorage",
                    "icon": "iconfont icon-r-refresh"
				}],
				"menu": "Storage & Service",
                "icon": "iconfont icon-r-refresh"
			}, {
				"child": [{
					"buttons": ["View", "Edit", "Delete", "Review", "Report"],
					"menu": "Order List",
					"menuJump": "List",
					"tableName": "useOrder",
                    "icon": "iconfont icon-r-paper"
				}],
				"menu": "Rental Orders",
                "icon": "iconfont icon-r-paper"
			}, {
				"child": [{
					"buttons": ["View", "Edit", "Delete", "Review"],
					"menu": "Return Review List",
					"menuJump": "List",
					"tableName": "carReturn",
                    "icon": "iconfont icon-r-right"
				}],
				"menu": "Return Reviews",
                "icon": "iconfont icon-r-right"
			}, {
				"child": [{
					"buttons": ["Add", "View", "Edit", "Delete"],
					"menu": "Admin List",
					"tableName": "admin",
                    "icon": "iconfont icon-r-user3"
				}],
				"menu": "Admin Access",
                "icon": "iconfont icon-r-user3"
			}],
			"frontMenu": [],
			"hasBackLogin": "Yes",
			"hasBackRegister": "No",
			"hasFrontLogin": "No",
			"hasFrontRegister": "No",
			"roleName": "Admin",
			"tableName": "admin"
		}, {
			"backMenu": [{
				"child": [{
					"buttons": ["View", "Use"],
					"menu": "Browse Cars",
					"menuJump": "List",
					"tableName": "carDispense",
                    "icon": "iconfont icon-r-bottom"
				}],
				"menu": "Browse Cars",
                "icon": "iconfont icon-r-bottom"
			}, {
				"child": [{
					"buttons": ["View", "Delete", "Pay", "Return"],
					"menu": "My Orders",
					"menuJump": "List",
					"tableName": "useOrder",
                    "icon": "iconfont icon-r-paper"
				}],
				"menu": "My Orders",
                "icon": "iconfont icon-r-paper"
			}, {
				"child": [{
					"buttons": ["View"],
					"menu": "Return History",
					"menuJump": "List",
					"tableName": "carReturn",
                    "icon": "iconfont icon-r-refresh"
				}],
				"menu": "Return History",
                "icon": "iconfont icon-r-refresh"
			}],
			"frontMenu": [],
			"hasBackLogin": "Yes",
			"hasBackRegister": "Yes",
			"hasFrontLogin": "Yes",
			"hasFrontRegister": "Yes",
			"roleName": "User",
			"tableName": "user"
		}]
	}
}
export default menu;
