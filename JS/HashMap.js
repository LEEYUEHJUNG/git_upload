window.onload = function () {
    class HashMap {
        #map;

        constructor() {
            this.#map = new Map();
        }

        put(key, value) {
            if (!key || this.#map.has(key)) {
                alert("「KEY」不可空白且不能重覆");
                return false;
            }

            this.#map.set(key, value);
            return true;
        }

        get(key) {
            return this.#map.get(key);
        }

        keys() {
            return Array.from(this.#map.keys());
        }

        contains(key) {
            return this.#map.has(key);
        }

        clear() {
            this.#map.clear();
        }

    }

    var hashMap = new HashMap();

    document.getElementById('putButton').onclick = function() {
        var key = document.getElementById('key').value;
        var value = document.getElementById('value').value;
        hashMap.put(key, value);
        displayResult();
    };
    
    document.getElementById('clearButton').onclick = function() {
        hashMap.clear();
        document.getElementById('key').value = '';
        document.getElementById('value').value = '';
        displayResult();
    };
    

    function displayResult() {
        var resultDiv = document.getElementById('result');
        var keysArray = hashMap.keys();
        var resultHtml = '';

        for (var i = 0; i < keysArray.length; i++) {
            var key = keysArray[i];
            var value = hashMap.get(key);
            resultHtml = key + ' : ' + value + '<br>';
        }

        resultDiv.innerHTML = resultHtml;
    }
}
