package io.github.phoenixvx.paperdoll;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.TranslatableText;

/**
 * @author Indigo A., xtreme8000
 */
@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
			ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent)
					.setTitle(new TranslatableText("text.paperdoll.title"));
			ConfigEntryBuilder entryBuilder = builder.entryBuilder();

			ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("text.paperdoll.general"));

			general.addEntry(entryBuilder.startIntField(new TranslatableText("text.paperdoll.option.render_height"),
					PaperDoll.config.render_height).setDefaultValue(50).setSaveConsumer(o -> {
						PaperDoll.config.render_height = (int) o;
						PaperDoll.saveConfig();
					}).build());

			general.addEntry(entryBuilder.startIntField(new TranslatableText("text.paperdoll.option.render_width"),
					PaperDoll.config.render_width).setDefaultValue(25).setSaveConsumer(o -> {
						PaperDoll.config.render_width = (int) o;
						PaperDoll.saveConfig();
					}).build());

			general.addEntry(entryBuilder
					.startIntField(new TranslatableText("text.paperdoll.option.rotation"), PaperDoll.config.rotation)
					.setDefaultValue(20).setSaveConsumer(o -> {
						PaperDoll.config.rotation = (int) o;
						PaperDoll.saveConfig();
					}).build());

			general.addEntry(
					entryBuilder.startBooleanToggle(new TranslatableText("text.paperdoll.option.dynamic_scale"),
							PaperDoll.config.dynamic_scale).setDefaultValue(false).setSaveConsumer(o -> {
								PaperDoll.config.dynamic_scale = (boolean) o;
								PaperDoll.saveConfig();
							}).build());

			general.addEntry(
					entryBuilder.startIntField(new TranslatableText("text.paperdoll.option.x"), PaperDoll.config.x)
							.setDefaultValue(10).setSaveConsumer(o -> {
								PaperDoll.config.x = (int) o;
								PaperDoll.saveConfig();
							}).build());

			general.addEntry(
					entryBuilder.startIntField(new TranslatableText("text.paperdoll.option.y"), PaperDoll.config.y)
							.setDefaultValue(10).setSaveConsumer(o -> {
								PaperDoll.config.y = (int) o;
								PaperDoll.saveConfig();
							}).build());

			general.addEntry(
					entryBuilder.startBooleanToggle(new TranslatableText("text.paperdoll.option.only_activity"),
							PaperDoll.config.only_activity).setDefaultValue(false).setSaveConsumer(o -> {
								PaperDoll.config.only_activity = (boolean) o;
								PaperDoll.saveConfig();
							}).build());

			general.addEntry(
					entryBuilder.startBooleanToggle(new TranslatableText("text.paperdoll.option.change_swim_fly"),
							PaperDoll.config.change_swim_fly).setDefaultValue(true).setSaveConsumer(o -> {
								PaperDoll.config.change_swim_fly = (boolean) o;
								PaperDoll.saveConfig();
							}).build());

			general.addEntry(
					entryBuilder.startBooleanToggle(new TranslatableText("text.paperdoll.option.render_vehicle"),
							PaperDoll.config.render_vehicle).setDefaultValue(true).setSaveConsumer(o -> {
								PaperDoll.config.render_vehicle = (boolean) o;
								PaperDoll.saveConfig();
							}).build());

			return builder.build();
		};
	}
}
