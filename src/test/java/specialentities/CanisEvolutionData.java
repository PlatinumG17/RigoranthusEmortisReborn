package specialentities;

//public class CanisEvolutionData {
//    public static final Codec<CanisEvolutionData> CODEC =
//            RecordCodecBuilder.create((dataInstance) ->
//                    dataInstance.group(Codec.INT.fieldOf("level").orElse(1).forGetter((level) -> level.level)).apply(dataInstance, CanisEvolutionData::new));
//    private final int level;
//
//    public CanisEvolutionData(int level) {this.level = Math.max(1, level);}
//
//    public CanisEvolutionData setLevel(int level) {return new CanisEvolutionData(level);}
//
//    public int getLevel() {return this.level;}
//
//    @Nonnull
//    public CanisEvolutionData withLevel(int level) {
//        return new CanisEvolutionData(level);
//    }
//}
