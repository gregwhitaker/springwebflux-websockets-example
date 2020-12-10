# springwebflux-websockets-example
![Build](https://github.com/gregwhitaker/springwebflux-websockets-example/workflows/Build/badge.svg)

An example of a reactive application using WebSockets and Spring WebFlux.

This example has a ticker service that generates stock prices and a client that allows you to get the changes for all stock tickers
streamed to you or allows you to subscribe to a single stock symbol and only get its changes streamed to you.

## Building the Example
Run the following command to build the example:

    ./gradlew clean build

## Running the Example
Follow the steps below to run the example application:

1. Start the `ticker-service` by running the following command:

        ./gradlew :ticker-service:bootRun
        
2. In a new terminal, run the following command to start the `ticker-client` and stream all stock tickers:

        ./gradlew :ticker-client:bootRun
        
    If successful, you will see prices streaming in as they change:

        [symbol: 'MCD', price: '217.04569062233392', volume: '3842459']
        [symbol: 'DWDP', price: '58.773564223813985', volume: '18439056']
        [symbol: 'V', price: '201.36407665460746', volume: '5068242']
        [symbol: 'JNJ', price: '54.7617904456654', volume: '4463539']
        [symbol: 'UTX', price: '26.037694121485202', volume: '5713780']
        [symbol: 'GS', price: '144.70238095792007', volume: '1470297']
        [symbol: 'GS', price: '426.9192178781588', volume: '3072623']
        
3. Now run the following command to subscribe only to Nike's (`NKE`) stock price changes:

        ./gradlew :ticker-client:bootRun --args="NKE"

    If successful, you will now only see `NKE` price change events streamed to you:

        [symbol: 'NKE', price: '182.39876004423166', volume: '127025']
        [symbol: 'NKE', price: '0.10341349711799158', volume: '4423692']
        [symbol: 'NKE', price: '0.43799636309273626', volume: '70043']

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/springwebflux-websockets-example/issues).

## License
MIT License

Copyright (c) 2020 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
