# BUBLS

Balanceable Untrusted BLS (BUBLS) allows untrusted hardware to do the validation work heavy lifting, while keeping the actual signing protected by a set of Guardiands.

## Inspiration
Political and hardware centralization is a real risk. An outage in AWS or a court order to the main US-based staking services would inflict a significant hit to the security of Ethereum2.

We have a dedicated network of home Node Runners willing to contribute to make Ethereum harder, better, stronger... but no tools to trust them with validating keys safely. We want to bring untrusted hardware to the validation party so anyone can do the validation work of Ethereum for a DAO.

## What it does
BUBLS allows untrusted hardware to do the validation work heavy lifting, while keeping the actual signing protected by a set of Guardiands of Ethereum. 

## How we built it
We modified Teku's Remote Signer to resend a signing duty to several BUBLS nodes. 
Such nodes, which we have created ourselves, hold a piece of a validating key generated through a MPC DKG, allowing us flexibility to remove and add signers. This key generation ceremony also eliminates the existence of a central "master key", a security risk.

## Challenges we ran into
We wanted to show an actual attestation in testnet, but the key generation piece took a bit too long and the 15h queue to enter a validator made it impossible to do so, so we discarded the DKG ceremony (which took a significant part of the time 🙁 )
Working with cutting edge cryptography and eth2 libraries with little use, we had to battle through import and compilation issues, less than optimal documentation and our own lack of sleep 😉

## Accomplishments that we're proud of
We validated the idea and we implemented the key pieces of it individually. 

## What we learned
A whole friggin lot. Alternatives to SSV, we deep dived into BLS and learned how to increase security of Ethereum with them. 
Remote signer has enormous potential.
DKGs seem to fit a lot of security use cases. 

## What's next for BUBLS
Complete PoC with attestations and proposals.
Create a DAO that can accept partial deposits (liquid staking)
Implement it in DAppNode.
