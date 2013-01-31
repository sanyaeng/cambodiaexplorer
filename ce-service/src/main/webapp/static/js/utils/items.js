(function(){
	var Item = function(key, obj) {
		var that = this;
		that.key = key;
		that.obj = obj;
	};
	Item.prototype = {
		getKey: function() {
			var that = this;
			return that.key;
		},
		getObject: function() {
			var that = this;
			return that.obj;
		},
		setObject: function(obj) {
			var that = this;
			that.obj = obj;
		}
	};
	if(typeof exports !== 'undefined') exports.Item = Item;
	else window.Item = Item;
})();