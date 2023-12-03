const{ expect } = require("chai");
const{ ethers } = require("hardhat");

describe("Mynft", function () {
    it("should mint and transfer an nft to someone", async function (){
        const Mynft = await ethers.getContractFactory("Mynft");
        const mynft = await Mynft.deploy();
        await mynft.deployed();
    })
})
const recipient = '0x8626f6940E2eb28930eFb4CeF49B2d1F2C9C1199';
const metadataUri = 'cid/test.png';

let balance = await mynft.balanceOf(recipient);
expect(balance).to.equal(0);
const newlyMintedNft = await mynft.payToMint(recipient , metadataURI , {value: ethers.utils.parseEther(0.05)});
await newlyMintedNft.wait();

balance = await mynft.balanceOf(recipient)
expect(balance).to.equal(1);
expect (await.mynft.isContentOwned(metadataURI)).to.equal(true);

