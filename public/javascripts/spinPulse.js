var LoadingIndicator = {
    items:       0,
    elementFade: 500,
    textFade:    250,
    element:     '#loadingindicator',
    textElement: '#loadingindicator .message',
    html:        '<div id="loadingindicator"><div class="indicator"><div class="circle1"></div><div class="circle2"></div></div><div class="message"></div></div>',

    start: function(text) {
        this._insert();

        if (!this.isLoading()) {
            this._show();
        }
        if (text != undefined && text != '') {
            this._text(text);
        }

        this.items++;
    },

    done: function() {
        if (this.items > 0) {
            this.items--;

            if (this.items == 0) {
                this._hide();
            }
        }
    },

    status: function(text) {
        if (this.isLoading()) {
            this._text(text);
        }
    },

    removeAll: function () {
        this.items = 0;
        this._remove();
    },

    isLoading: function() {
        return (this.items > 0);
    },

    _hide: function() { 
        jQuery(this.element).fadeOut(this.elementFade);
        this._text('');
    },
    _show: function() {
        jQuery(this.element).fadeIn(this.elementFade);
    },
    _text: function(text) {
        jQuery(this.textElement).fadeOut(this.textFade, function() {
            jQuery(this).text(text).fadeIn(this.textFade);
        });
    },
    _insert: function() {
        if (jQuery(this.element).length == 0) {
            jQuery('body').append(this.html);
        }
    },
    _remove: function() {
        jQuery(this.element).fadeOut(this.elementFade, function() {
            jQuery(this).remove();
        });
    }

};