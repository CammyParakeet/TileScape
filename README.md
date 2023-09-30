# TileScape
Tile based / Checkerboard API &amp; Plugin for MC


## WIP notes
Roadmap:
- Add support for tile based board games eg
  - Chess, Checkers
  - Go, Shogi
  - Scrabble
  - Backgammon?
  - potentially Chinese Checkers later

- Switchup from block-based selection raytracing -> entity raytracing on interaction entities?
  - This should allow for games to be globally scalable, eg entire board & pieces be the size of 1 block, or 8 blocks, or even larger etc

- Command & menu management
- Allow spectators
- Have gamelogic server-side but all rendering client-side
  - eg packet based fake entities
  - allows multiple games at same location? Might be bad idea
  - allows player based customization

- larger teamsizes - co-op mode
- stats saving & tracking
- AI implementations
