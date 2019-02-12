async function hello() {
    return "world";
}
const helloPromise = hello();
helloPromise.then(console.log);

async function hello2() {
    await stall(1000);
    throw new Error("get lost");
}

const hello2Promise = hello2();
hello2Promise.catch(reason => {
    if (reason instanceof Error) console.log(reason.message);
    else console.log("rejected because " + JSON.stringify(reason));
});


// from https://staxmanade.com/2016/07/easily-simulate-slow-async-calls-using-javascript-async-await/
async function stall(stallTime = 3000) {
    await new Promise(resolve => setTimeout(resolve, stallTime));
}
