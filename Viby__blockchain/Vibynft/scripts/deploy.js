const hre = require("hardhat");

async function main() {

  const Mynft = await hre.ethers.getContractFactory("Mynft");
  const mynft = await FiredGuys.deploy();

  await mynft.deployed();

  console.log("My NFT deployed to:", Mynft.address);
}


main()
  .then(() => process.exit(0))
  .catch((error) => {
    console.error(error);
    process.exit(1);
  });