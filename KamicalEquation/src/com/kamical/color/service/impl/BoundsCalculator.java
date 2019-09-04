/*package com.kamical.color.service.impl;

import javax.vecmath.Point2d;

import com.kamical.color.service.IAtom;
import com.kamical.color.service.IAtomContainer;
import com.kamical.color.service.IAtomContainerSet;
import com.kamical.color.service.IChemModel;
import com.kamical.color.service.IReaction;
import com.kamical.color.service.IReactionSet;

import java.awt.geom.Rectangle2D;

*//**
 * Utility class for calculating the 2D bounding rectangles (bounds)
 * of various IChemObject subtypes - IChemModel, IReactionSet, IReaction,
 * IAtomContainerSet, and IAtomContainer.
 *
 * @author maclean
 * @cdk.module renderbasic
 * @cdk.githash
 *//*
public class BoundsCalculator {

    *//**
     * Calculate the bounding rectangle for a chem model.
     *
     * @param chemModel the chem model to use
     * @return the bounding rectangle of the chem model
     *//*
    public static Rectangle2D calculateBounds(IChemModel chemModel) {
        IAtomContainerSet moleculeSet = chemModel.getMoleculeSet();
        IReactionSet reactionSet = chemModel.getReactionSet();
        Rectangle2D totalBounds = new Rectangle2D.Double();
        if (moleculeSet != null) {
            totalBounds = totalBounds.createUnion(calculateBounds(moleculeSet));
        }
        if (reactionSet != null) {
            totalBounds = totalBounds.createUnion(calculateBounds(reactionSet));
        }
        return totalBounds;
    }

    *//**
     * Calculate the bounding rectangle for a reaction set.
     *
     * @param reactionSet the reaction set to use
     * @return the bounding rectangle of the reaction set
     *//*
    public static Rectangle2D calculateBounds(IReactionSet reactionSet) {
        Rectangle2D totalBounds = new Rectangle2D.Double();
        for (IReaction reaction : reactionSet.reactions()) {
            Rectangle2D reactionBounds = calculateBounds(reaction);
            if (totalBounds.isEmpty()) {
                totalBounds = reactionBounds;
            } else {
                Rectangle2D.union(totalBounds, reactionBounds, totalBounds);
            }
        }
        return totalBounds;
    }

    *//**
     * Calculate the bounding rectangle for a reaction.
     *
     * @param reaction the reaction to use
     * @return the bounding rectangle of the reaction
     *//*
    public static Rectangle2D calculateBounds(IReaction reaction) {
        // get the participants in the reaction
        IAtomContainerSet reactants = reaction.getReactants();
        IAtomContainerSet products = reaction.getProducts();
        if (reactants == null || products == null) return null;

        // determine the bounds of everything in the reaction
        Rectangle2D reactantsBounds = calculateBounds(reactants);
        return reactantsBounds.createUnion(calculateBounds(products));
    }

    *//**
     * Calculate the bounding rectangle for a molecule set.
     *
     * @param moleculeSet the molecule set to use
     * @return the bounding rectangle of the molecule set
     *//*
    public static Rectangle2D calculateBounds(IAtomContainerSet moleculeSet) {
        Rectangle2D totalBounds = new Rectangle2D.Double();
        for (int i = 0; i < moleculeSet.getAtomContainerCount(); i++) {
            IAtomContainer container = moleculeSet.getAtomContainer(i);
            Rectangle2D acBounds = calculateBounds(container);
            if (totalBounds.isEmpty()) {
                totalBounds = acBounds;
            } else {
                Rectangle2D.union(totalBounds, acBounds, totalBounds);
            }
        }
        return totalBounds;
    }

    *//**
     * Calculate the bounding rectangle for an atom container.
     *
     * @param atomContainer the atom container to use
     * @return the bounding rectangle of the atom container
     *//*
    public static Rectangle2D calculateBounds(IAtomContainer atomContainer) {
        // this is essential, otherwise a rectangle
        // of (+INF, -INF, +INF, -INF) is returned!
        if (atomContainer.getAtomCount() == 0) {
            return new Rectangle2D.Double();
        } else if (atomContainer.getAtomCount() == 1) {
            Point2d point = atomContainer.getAtom(0).getPoint2d();
            if (point == null) {
                throw new IllegalArgumentException("Cannot calculate bounds when 2D coordinates are missing.");
            }
            return new Rectangle2D.Double(point.x, point.y, 0, 0);
        }

        double xmin = Double.POSITIVE_INFINITY;
        double xmax = Double.NEGATIVE_INFINITY;
        double ymin = Double.POSITIVE_INFINITY;
        double ymax = Double.NEGATIVE_INFINITY;

        for (IAtom atom : atomContainer.atoms()) {
            Point2d point = atom.getPoint2d();
            if (point == null) {
                throw new IllegalArgumentException("Cannot calculate bounds when 2D coordinates are missing.");
            }
            xmin = Math.min(xmin, point.x);
            xmax = Math.max(xmax, point.x);
            ymin = Math.min(ymin, point.y);
            ymax = Math.max(ymax, point.y);
        }
        double width = xmax - xmin;
        double height = ymax - ymin;
        return new Rectangle2D.Double(xmin, ymin, width, height);
    }

}
*/