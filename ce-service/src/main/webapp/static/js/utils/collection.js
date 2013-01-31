(function(){
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
	var Collection = function() {
		var that = this;
		that.key; //key to map the item
		that.items;//array of item
		that.keys;//list of keys
		that._getActuallItem = function(key) {
			if(typeof that.items === 'undefined') {
				return;
			}
			var item;
			for(var i in that.items) {
				if(that.items[i].getKey() == key) {
					item = that.items[i];
					break;
				}
			}
			return item;
		};
		that._updateItem = function(key, obj) {
			if(typeof that.items === 'undefined') {
				return;
			}
			var item, index;
			for(var i in that.items) {
				if(that.items[i].getKey() == key) {
					item = that.items[i];
					index = i;
					break;
				}
			}
			item.setObject(obj);
			that.items.splice(index, 1, item);
		};
	};
	Collection.prototype = {
		add: function(key, obj) {
			var that = this;
			var item;
			//if the given key is exist, then just update the item
			
			if(this.isExist(key)) {
				//find the index of the item in items array and splice it out
				that._updateItem(key);
				return;
			}
			item = new Item(key, obj);
			if(typeof that.items === 'undefined') {
				that.items = new Array();
			}
			console.log(typeof that.keys);
			if(typeof that.keys === 'undefined') {
				that.keys = new Array();
			}
			that.items.push(item);
			that.keys.push(key);
		},
		remove: function(key) {
			var that = this;
			if(typeof that.items === 'undefined') {
				console.log("nothing to be removed!!!");
				return;
			}
			var item;
			var found = false;
			for(var k in that.keys) {
				if(that.keys[k] == key) {
					that.keys.slice(k, 1);
					found = true;
				}
			}
			if(!found) {
				console.log("Item not found with the provided key");
				return;
			}
			for(var i in that.items) {
				item = that.items[i];
				if(item.gatKey() == key) {
					that.items.slice(i, 1);
					break;
				}
			}
		},
		isExist: function(key) {
			var that = this;
			var found = false;
			for(var i in that.keys) {
				if(that.keys[i] == key) {
					found = true;
				}
			}
			return found;
		},
		count: function() {
			var that = this;
			return that.items.length;
		},
		getItem: function(key) {
			var that = this;
			if(typeof that.items === 'undefined') {
				console.log("nothing to be removed!!!");
				return;
			}
			var item;
			for(var i in that.items) {
				if(that.items[i].getKey() == key) {
					item = that.items[i].getObject();
					break;
				}
			}
			return item;
		},
		removeAll: function() {
			var that = this;
			if(typeof that.items === 'undefined') {
				return;
			}
			if(typeof that.keys === 'undefined') {
				return;
			}
			that.keys.splice(0, that.keys.length);
			that.items.splice(0, that.items.length);
		},
		getKeys: function() {
			var that = this;
			if(typeof that.keys === 'undefined') {
				return;
			}
			return that.keys;
		}
	};
	if(typeof exports !== 'undefined') exports.Collection = Collection;
	else window.Collection = Collection;
})();