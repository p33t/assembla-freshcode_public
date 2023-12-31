package pkg.builder.biweekly.component;

import biweekly.component.VAlarm;

/**
 * Generated by BuilderGen
 *
 * @see biweekly.component.VAlarm
 */
@SuppressWarnings("UnusedDeclaration")
@pkg.builder.BeanBuilder(VAlarm.class)
public class VAlarmBuilder extends pkg.builder.Construct.Parent<VAlarmBuilder> {
    public final VAlarm vAlarm;

    public VAlarmBuilder(VAlarm v) {
        vAlarm = v;
    }

    /**
     * @see biweekly.component.VAlarm#VAlarm(biweekly.property.Action, biweekly.property.Trigger)
     */
    public VAlarmBuilder(biweekly.property.Action v0, biweekly.property.Trigger v1) {
        this(new VAlarm(v0, v1));
    }

    /**
     * @see biweekly.component.VAlarm#addAttachment(biweekly.property.Attachment)
     */
    public VAlarmBuilder addAttachment(biweekly.property.Attachment v0) {
        vAlarm.addAttachment(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#addAttendee(biweekly.property.Attendee)
     */
    public VAlarmBuilder addAttendee(biweekly.property.Attendee v0) {
        vAlarm.addAttendee(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#addComponent(biweekly.component.ICalComponent)
     */
    public VAlarmBuilder addComponent(biweekly.component.ICalComponent v0) {
        vAlarm.addComponent(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#addExperimentalProperty(java.lang.String, java.lang.String)
     */
    public VAlarmBuilder addExperimentalProperty(java.lang.String v0, java.lang.String v1) {
        vAlarm.addExperimentalProperty(v0, v1);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#addExperimentalProperty(java.lang.String, biweekly.ICalDataType, java.lang.String)
     */
    public VAlarmBuilder addExperimentalProperty(java.lang.String v0, biweekly.ICalDataType v1, java.lang.String v2) {
        vAlarm.addExperimentalProperty(v0, v1, v2);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#addProperty(biweekly.property.ICalProperty)
     */
    public VAlarmBuilder addProperty(biweekly.property.ICalProperty v0) {
        vAlarm.addProperty(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setAction(biweekly.property.Action)
     */
    public VAlarmBuilder action(biweekly.property.Action v0) {
        vAlarm.setAction(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setComponent(biweekly.component.ICalComponent)
     */
    public VAlarmBuilder component(biweekly.component.ICalComponent v0) {
        vAlarm.setComponent(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setComponent(java.lang.Class, T)
     */
    public <T extends biweekly.component.ICalComponent> VAlarmBuilder component(java.lang.Class<T> v0, T v1) {
        vAlarm.setComponent(v0, v1);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setDescription(biweekly.property.Description)
     */
    public VAlarmBuilder description(biweekly.property.Description v0) {
        vAlarm.setDescription(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setDescription(java.lang.String)
     */
    public VAlarmBuilder description(java.lang.String v0) {
        vAlarm.setDescription(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setDuration(biweekly.property.DurationProperty)
     */
    public VAlarmBuilder duration(biweekly.property.DurationProperty v0) {
        vAlarm.setDuration(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setDuration(biweekly.util.Duration)
     */
    public VAlarmBuilder duration(biweekly.util.Duration v0) {
        vAlarm.setDuration(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setExperimentalProperty(java.lang.String, java.lang.String)
     */
    public VAlarmBuilder experimentalProperty(java.lang.String v0, java.lang.String v1) {
        vAlarm.setExperimentalProperty(v0, v1);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setExperimentalProperty(java.lang.String, biweekly.ICalDataType, java.lang.String)
     */
    public VAlarmBuilder experimentalProperty(java.lang.String v0, biweekly.ICalDataType v1, java.lang.String v2) {
        vAlarm.setExperimentalProperty(v0, v1, v2);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setProperty(biweekly.property.ICalProperty)
     */
    public VAlarmBuilder property(biweekly.property.ICalProperty v0) {
        vAlarm.setProperty(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setProperty(java.lang.Class, T)
     */
    public <T extends biweekly.property.ICalProperty> VAlarmBuilder property(java.lang.Class<T> v0, T v1) {
        vAlarm.setProperty(v0, v1);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setRepeat(biweekly.property.Repeat)
     */
    public VAlarmBuilder repeat(biweekly.property.Repeat v0) {
        vAlarm.setRepeat(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setRepeat(java.lang.Integer)
     */
    public VAlarmBuilder repeat(java.lang.Integer v0) {
        vAlarm.setRepeat(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setRepeat(int, biweekly.util.Duration)
     */
    public VAlarmBuilder repeat(int v0, biweekly.util.Duration v1) {
        vAlarm.setRepeat(v0, v1);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setSummary(biweekly.property.Summary)
     */
    public VAlarmBuilder summary(biweekly.property.Summary v0) {
        vAlarm.setSummary(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setSummary(java.lang.String)
     */
    public VAlarmBuilder summary(java.lang.String v0) {
        vAlarm.setSummary(v0);
        return this;
    }

    /**
     * @see biweekly.component.VAlarm#setTrigger(biweekly.property.Trigger)
     */
    public VAlarmBuilder trigger(biweekly.property.Trigger v0) {
        vAlarm.setTrigger(v0);
        return this;
    }
}
